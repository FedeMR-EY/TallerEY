package ey.com.personas.mspersonas.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ey.com.personas.mspersonas.dto.CreateAccountMessageResponse;
import ey.com.personas.mspersonas.dto.CreateCardMessageResponse;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonasConsumer {
  private final ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  public PersonasConsumer(ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses) {
    this.pendingResponses = pendingResponses;
  }

  @KafkaListener(topics = "ms-cuentas-to-ms-personas", groupId = "group_id")
  public void consumeFromMsCuentas(Object message) {
    try {
      var consumerRecord = (ConsumerRecord<String, String>) message;
      var createAccountMessageResponse =
          objectMapper.readValue(consumerRecord.value(), CreateAccountMessageResponse.class);

      var pendingResponse = pendingResponses.get(createAccountMessageResponse.uuid());
      if (pendingResponse != null) {
        if (createAccountMessageResponse.created()) {
          pendingResponse.complete(true);
        } else {
          log.error(
              "La cuenta no fue creada correctamente para UUID: {}",
              createAccountMessageResponse.uuid());
          pendingResponse.complete(false); // Completar con false si no se creó correctamente
        }
      } else {
        log.warn(
            "No se encontró ninguna respuesta pendiente para UUID: {}",
            createAccountMessageResponse.uuid());
      }
    } catch (JsonProcessingException e) {
      log.error("Error al procesar el mensaje de ms-cuentas: {}", e.getMessage(), e);
    } catch (Exception e) {
      log.error("Error inesperado en consumeFromMsCuentas: {}", e.getMessage(), e);
    }
  }

  @KafkaListener(topics = "ms-tarjetas-to-ms-personas", groupId = "group_id")
  public void consumeFromMsTarjetas(Object message) {
    try {
      var consumerRecord = (ConsumerRecord<String, String>) message;
      var createCardMessageResponse =
          objectMapper.readValue(consumerRecord.value(), CreateCardMessageResponse.class);

      var pendingResponse = pendingResponses.get(createCardMessageResponse.uuid());
      if (pendingResponse != null) {
        if (createCardMessageResponse.created()) {
          pendingResponse.complete(true);
        } else {
          log.error(
              "La tarjeta no fue creada correctamente para UUID: {}",
              createCardMessageResponse.uuid());
          pendingResponse.complete(false); // Completar con false si no se creó correctamente
        }
      } else {
        log.warn(
            "No se encontró ninguna respuesta pendiente para UUID: {}",
            createCardMessageResponse.uuid());
      }
    } catch (JsonProcessingException e) {
      log.error("Error al procesar el mensaje de ms-tarjetas: {}", e.getMessage(), e);
    } catch (Exception e) {
      log.error("Error inesperado en consumeFromMsTarjetas: {}", e.getMessage(), e);
    }
  }
}
