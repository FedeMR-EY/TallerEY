package ey.com.personas.mspersonas.service.business;

import ey.com.personas.mspersonas.dto.CreateAccountMessage;
import ey.com.personas.mspersonas.dto.CreateCardMessage;
import ey.com.personas.mspersonas.dto.RegistrarPersonaRequest;
import ey.com.personas.mspersonas.dto.RegistrarPersonaResponse;
import ey.com.personas.mspersonas.dto.api.renaper.RenaperResponse;
import ey.com.personas.mspersonas.dto.api.veraz.VerazResponse;
import ey.com.personas.mspersonas.dto.api.worldsys.WorldSysResponse;
import ey.com.personas.mspersonas.exception.ConflictException;
import ey.com.personas.mspersonas.model.Domicilio;
import ey.com.personas.mspersonas.model.EstadoUsuario;
import ey.com.personas.mspersonas.model.TipoUsuario;
import ey.com.personas.mspersonas.model.Usuarios;
import ey.com.personas.mspersonas.service.DomicilioService;
import ey.com.personas.mspersonas.service.EstadoUsuarioService;
import ey.com.personas.mspersonas.service.TipoUsuarioService;
import ey.com.personas.mspersonas.service.UsuariosService;
import ey.com.personas.mspersonas.service.connectors.NodeAppHttpClient;
import ey.com.personas.mspersonas.service.kafka.PersonasProducer;
import ey.com.personas.mspersonas.shared.enumeration.AccountTypes;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@Slf4j
public class PersonasBusinessService {
  private final UsuariosService usuariosService;
  private final TipoUsuarioService tipoUsuarioService;
  private final EstadoUsuarioService estadoUsuarioService;
  private final DomicilioService domicilioService;
  private final NodeAppHttpClient nodeAppHttpClient;
  private final PersonasProducer personasProducer;
  private final ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses;

  @Autowired
  public PersonasBusinessService(
      UsuariosService usuariosService,
      TipoUsuarioService tipoUsuarioService,
      EstadoUsuarioService estadoUsuarioService,
      DomicilioService domicilioService,
      NodeAppHttpClient nodeAppHttpClient,
      PersonasProducer personasProducer,
      ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses) {
    this.usuariosService = usuariosService;
    this.tipoUsuarioService = tipoUsuarioService;
    this.estadoUsuarioService = estadoUsuarioService;
    this.domicilioService = domicilioService;
    this.nodeAppHttpClient = nodeAppHttpClient;
    this.personasProducer = personasProducer;
    this.pendingResponses = pendingResponses;
  }

  public ResponseEntity<RegistrarPersonaResponse> registrarPersona(
      RegistrarPersonaRequest request, BindingResult result) {
    try {

      if (result.hasErrors()) {
        throw new IllegalArgumentException();
      }

      var usuario = usuariosService.findByDni(request.dni());

      if (usuario.isPresent()) {
        var user = usuario.get();
        if (user.getTipo().getDescripcion().equals("Cliente")
            || !user.getEstado().getDescripcion().equals("Activo")) {
          return new ResponseEntity<>(
              new RegistrarPersonaResponse(
                  "El usuario ya es un cliente registrado o la cuenta no está activa"),
              HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }

      CompletableFuture<VerazResponse> verazFuture =
          nodeAppHttpClient.getVerazDetails(request.dni());
      CompletableFuture<RenaperResponse> renaperFuture =
          nodeAppHttpClient.getRenaperDetails(request.dni());
      CompletableFuture<WorldSysResponse> worldSysFuture =
          nodeAppHttpClient.getWorldSysDetails(request.dni());

      // Esperar a que todas las respuestas asíncronas se completen
      CompletableFuture.allOf(verazFuture, renaperFuture, worldSysFuture).join();

      VerazResponse verazResponse = verazFuture.get();
      RenaperResponse renaperResponse = renaperFuture.get();
      WorldSysResponse worldSysResponse = worldSysFuture.get();

      if (verazResponse == null || renaperResponse == null || worldSysResponse == null) {
        return new ResponseEntity<>(
            new RegistrarPersonaResponse("Hubo un problema al consultar información externa"),
            HttpStatus.INTERNAL_SERVER_ERROR);
      }

      var accType =
          AccountTypes.obtenerCuenta(
              renaperResponse.response().get(0).isAuthorize(),
              verazResponse.response().get(0).score(),
              worldSysResponse.response().get(0).isTerrotist(),
              request.sueldoBruto());

      if (accType.equals(AccountTypes.NO_ELIGIBLE)) {
        return new ResponseEntity<>(
            new RegistrarPersonaResponse("Persona no apta para la creacion de una cuenta"),
            HttpStatus.INTERNAL_SERVER_ERROR);
      }

      var newUser = new Usuarios();
      var tipoUsuario = (Optional<TipoUsuario>) tipoUsuarioService.findById(1);
      var estadoUsuario = (Optional<EstadoUsuario>) estadoUsuarioService.findById(1);
      var domicilio = new Domicilio();

      newUser.setNombre(request.nombre());
      newUser.setApellido(request.apellido());
      newUser.setDni(request.dni());
      newUser.setTipo(tipoUsuario.get());
      newUser.setEstado(estadoUsuario.get());

      var personNumber = (Integer) usuariosService.save(newUser);

      domicilio.setCalle(request.calle());
      domicilio.setPersnum(personNumber);
      domicilio.setProvincia(request.provincia());
      domicilio.setLocalidad(request.localidad());
      domicilio.setNumero(request.numero());

      domicilioService.save(domicilio);

      var createAccountMessage =
          new CreateAccountMessage(accType, request, UUID.randomUUID(), personNumber);
      var createCardMessage = new CreateCardMessage(accType, UUID.randomUUID());

      var cuentasFuture = new CompletableFuture<Boolean>();
      var tarjetasFuture = new CompletableFuture<Boolean>();

      pendingResponses.put(createAccountMessage.uuid(), cuentasFuture);
      pendingResponses.put(createCardMessage.uuid(), tarjetasFuture);

      personasProducer.sendMessageToMsCuentas(createAccountMessage);

      // Se envía el mensaje para crear la tarjeta solo si el tipo de cuenta lo permite
      if (accType != AccountTypes.BASIC) {
        personasProducer.sendMessageToMsTarjetas(createCardMessage);
      }

      // Acciones asíncronas para cuando lleguen las respuestas
      cuentasFuture.thenAccept(
          cuentasResult -> {
            if (!cuentasResult) {
              // Manejar error en la creación de cuenta
              log.error("Error creando la cuenta para UUID: " + createAccountMessage.uuid());
              throw new ConflictException("Hubo un error al crear la cuenta");
            } else {
              log.info(
                  "Cuenta creada exitosamente para persona con UUID: "
                      + createAccountMessage.uuid());
            }
          });

      tarjetasFuture.thenAccept(
          tarjetasResult -> {
            if (accType != AccountTypes.BASIC && !tarjetasResult) {
              // Manejar error en la creación de tarjeta
              log.error("Error creando la tarjeta para UUID: " + createCardMessage.uuid());
              throw new ConflictException("Hubo un error al crear la tarjeta");
            } else {
              log.info(
                  "Tarjeta creada exitosamente para persona con UUID: " + createCardMessage.uuid());
            }
          });

      return new ResponseEntity<>(
          new RegistrarPersonaResponse("Se inició el proceso de creación de cuenta y tarjeta"),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      log.error("Faltan parámetros: {}", (Object) e.getStackTrace());
      return new ResponseEntity<>(
          new RegistrarPersonaResponse("Faltan parámetros"), HttpStatus.BAD_REQUEST);
    } catch (ConflictException e) {
      log.error(
          "Algo salió mal crear la(s) cuenta(s) o tarjeta(s) asociadas: {}",
          (Object) e.getStackTrace());
      return new ResponseEntity<>(
          new RegistrarPersonaResponse(
              "Algo salió mal crear la(s) cuenta(s) o tarjeta(s) asociadas."),
          HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception e) {
      log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
      return new ResponseEntity<>(
          new RegistrarPersonaResponse("Ocurrió un error inesperado"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
