package ey.com.personas.mspersonas.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ey.com.personas.mspersonas.dto.CreateAccountMessageResponse;
import ey.com.personas.mspersonas.dto.CreateCardMessageResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PersonasConsumer {
  private final ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  public PersonasConsumer(ConcurrentHashMap<UUID, CompletableFuture<Boolean>> pendingResponses) {
    this.pendingResponses = pendingResponses;
  }

  @KafkaListener(topics = "ms-cuentas-to-ms-personas", groupId = "group_id")
  public void consumeFromMsCuentas(Object message) throws JsonProcessingException {
    var consumerRecord = (ConsumerRecord<String, String>) message;
    var createAccountMessageResponse =
        (Optional<CreateAccountMessageResponse>)
            objectMapper.readValue(consumerRecord.value(), Optional.class);

    if (createAccountMessageResponse.get().created()) {
      var pendingResponse = pendingResponses.get(createAccountMessageResponse.get().uuid());
      if (pendingResponse != null) {
        pendingResponse.complete(true);
      }
    }
  }

  @KafkaListener(topics = "ms-tarjetas-to-ms-personas", groupId = "group_id")
  public void consumeFromMsTarjetas(Object message) throws JsonProcessingException {
    var consumerRecord = (ConsumerRecord<String, String>) message;
    var createCardMessage =
        (Optional<CreateCardMessageResponse>)
            objectMapper.readValue(consumerRecord.value(), Optional.class);

    if (createCardMessage.get().created()) {
      var pendingResponse = pendingResponses.get(createCardMessage.get().uuid());
      if (pendingResponse != null) {
        pendingResponse.complete(true);
      }
    }
  }
}
