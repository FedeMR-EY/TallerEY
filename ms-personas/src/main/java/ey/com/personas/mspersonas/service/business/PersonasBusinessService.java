package ey.com.personas.mspersonas.service.business;

import ey.com.personas.mspersonas.dto.CreateAccountMessage;
import ey.com.personas.mspersonas.dto.CreateCardMessage;
import ey.com.personas.mspersonas.dto.RegistrarPersonaRequest;
import ey.com.personas.mspersonas.dto.RegistrarPersonaResponse;
import ey.com.personas.mspersonas.exception.ConflictException;
import ey.com.personas.mspersonas.model.EstadoUsuario;
import ey.com.personas.mspersonas.model.TipoUsuario;
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

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class PersonasBusinessService {
  private final UsuariosService usuariosService;
  private final NodeAppHttpClient nodeAppHttpClient;
  private final PersonasProducer personasProducer;
  private final ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses;
  @Autowired
  public PersonasBusinessService(
          UsuariosService usuariosService,
          NodeAppHttpClient nodeAppHttpClient,
          PersonasProducer personasProducer, ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses) {
    this.usuariosService = usuariosService;
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

      var verazResponse = nodeAppHttpClient.getVerazDetails(request.dni());
      var renaperResponse = nodeAppHttpClient.getRenaperDetails(request.dni());
      var worldSysResponse = nodeAppHttpClient.getWorldSysDetails(request.dni());

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
      var tipoUsuario = new TipoUsuario();
      var estadoUsuario = new EstadoUsuario();

      estadoUsuario.setDescripcion("Activo");

      tipoUsuario.setDescripcion("Cliente");

      newUser.setNombre(request.nombre());
      newUser.setApellido(request.apellido());
      newUser.setDni(request.dni());
      newUser.setTipo(tipoUsuario);
      newUser.setEstado(estadoUsuario);

      var personNumber = (Integer) usuariosService.save(newUser);

      var createAccountMessage = new CreateAccountMessage(accType, request, UUID.randomUUID(), personNumber);
      var createCardMessage = new CreateCardMessage(accType, UUID.randomUUID());

      var cuentasFuture = new CompletableFuture<Boolean>();
      var tarjetasFuture = new CompletableFuture<Boolean>();

      pendingResponses.put(createAccountMessage.uuid(), cuentasFuture);
      pendingResponses.put(createCardMessage.uuid(), tarjetasFuture);

      personasProducer.sendMessageToMsCuentas(createAccountMessage);
      if(accType != AccountTypes.BASIC){
        personasProducer.sendMessageToMsTarjetas(createCardMessage);
      }

      // Esperar las respuestas
      var cuentasResult = cuentasFuture.get(35, TimeUnit.SECONDS);
      var tarjetasResult = tarjetasFuture.get(35, TimeUnit.SECONDS);

      if (!cuentasResult && !tarjetasResult){
        throw new ConflictException();
      }

      return new ResponseEntity<>(
          new RegistrarPersonaResponse("Se creó la cuenta correctamente"), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      log.error("Faltan parámetros: {}", (Object) e.getStackTrace());
      return new ResponseEntity<>(
          new RegistrarPersonaResponse("Faltan parámetros"), HttpStatus.BAD_REQUEST);
    } catch (ConflictException e){
      usuariosService.deleteByDni(request.dni());
      log.error("Algo salió mal crear la(s) cuenta(s) o tarjeta(s) asociadas: {}", (Object) e.getStackTrace());
      return new ResponseEntity<>(
              new RegistrarPersonaResponse("Algo salió mal crear la(s) cuenta(s) o tarjeta(s) asociadas."),
              HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (TimeoutException e) {
      usuariosService.deleteByDni(request.dni());
      log.error("Timeout esperando las respuestas de Kafka: {}", (Object) e.getStackTrace());
      return new ResponseEntity<>(
              new RegistrarPersonaResponse("Timeout al crear la(s) cuenta(s) o tarjeta(s)"),
              HttpStatus.INTERNAL_SERVER_ERROR);
    }catch (InterruptedException e) {
      usuariosService.deleteByDni(request.dni());
      Thread.currentThread().interrupt();
      log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
      return new ResponseEntity<>(
              new RegistrarPersonaResponse("Ocurrió un error inesperado"),
              HttpStatus.INTERNAL_SERVER_ERROR);
    }catch (Exception e) {
      usuariosService.deleteByDni(request.dni());
      log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
      return new ResponseEntity<>(
          new RegistrarPersonaResponse("Ocurrió un error inesperado"),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
