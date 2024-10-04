package ey.com.personas.mspersonas.service.business;

import ey.com.personas.mspersonas.dto.CreateAccountMessage;
import ey.com.personas.mspersonas.dto.RegistrarPersonaRequest;
import ey.com.personas.mspersonas.dto.RegistrarPersonaResponse;
import ey.com.personas.mspersonas.dto.api.renaper.RenaperResponse;
import ey.com.personas.mspersonas.dto.api.veraz.VerazResponse;
import ey.com.personas.mspersonas.dto.api.worldsys.WorldSysResponse;
import ey.com.personas.mspersonas.model.Usuarios;
import ey.com.personas.mspersonas.service.UsuariosService;
import ey.com.personas.mspersonas.service.connectors.NodeAppHttpClient;
import ey.com.personas.mspersonas.service.kafka.PersonasProducer;
import ey.com.personas.mspersonas.shared.enumeration.AccountTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@Slf4j
public class PersonasBusinessService {
    private final UsuariosService usuariosService;
    private final NodeAppHttpClient nodeAppHttpClient;
    private final PersonasProducer personasProducer;

    @Autowired
    public PersonasBusinessService(UsuariosService usuariosService, NodeAppHttpClient nodeAppHttpClient, PersonasProducer personasProducer) {
        this.usuariosService = usuariosService;
        this.nodeAppHttpClient = nodeAppHttpClient;
        this.personasProducer = personasProducer;
    }

    public ResponseEntity<RegistrarPersonaResponse> registrarPersona(RegistrarPersonaRequest request, BindingResult result) {
        try {

            if (result.hasErrors()) {
                throw new IllegalArgumentException();
            }

            Optional<Usuarios> usuario = usuariosService.findByDni(request.getDni());

            if (usuario.isPresent()) {
                Usuarios user = usuario.get();
                if (user.getTipo().getDescripcion().equals("Cliente") || !user.getEstado().getDescripcion().equals("Activa")){
                    return new ResponseEntity<>(RegistrarPersonaResponse.builder().message("El usuario ya es un cliente registrado o la cuenta no está activa").build(),HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            VerazResponse verazResponse = nodeAppHttpClient.getVerazDetails(request.getDni());
            RenaperResponse renaperResponse = nodeAppHttpClient.getRenaperDetails(request.getDni());
            WorldSysResponse worldSysResponse = nodeAppHttpClient.getWorldSysDetails(request.getDni());

            if(verazResponse == null || renaperResponse == null || worldSysResponse == null){
                return new ResponseEntity<>(RegistrarPersonaResponse.builder().message("Hubo un problema al consultar información externa").build(),HttpStatus.INTERNAL_SERVER_ERROR);
            }

            AccountTypes accType = AccountTypes.obtenerCuenta(renaperResponse.getResponse().get(0).isAuthorize(),
                    verazResponse.getResponse().get(0).getScore(),
                    worldSysResponse.getResponse().get(0).isTerrotist(),
                    request.getSueldoBruto());

            if (accType.equals(AccountTypes.NO_ELIGIBLE)) {
                return new ResponseEntity<>(RegistrarPersonaResponse.builder().message("Persona no apta para la creacion de una cuenta").build(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CreateAccountMessage createAccountMessage = new CreateAccountMessage();
            createAccountMessage.setAccountType(accType);
            createAccountMessage.setRequest(request);

            personasProducer.sendMessage(createAccountMessage);

            return new ResponseEntity<>(RegistrarPersonaResponse.builder().message("Se creó la cuenta correctamente").build(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Faltan parámetros: {}", (Object) e.getStackTrace());
            return new ResponseEntity<>(RegistrarPersonaResponse.builder().message("Faltan parámetros").build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
            return new ResponseEntity<>(RegistrarPersonaResponse.builder().message("Ocurrió un error inesperado").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
