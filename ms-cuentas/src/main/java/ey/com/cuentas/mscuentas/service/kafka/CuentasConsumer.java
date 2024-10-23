package ey.com.cuentas.mscuentas.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import ey.com.cuentas.mscuentas.dto.CreateAccountMessage;
import ey.com.cuentas.mscuentas.service.business.CuentasBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CuentasConsumer {
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final CuentasProducer cuentasProducer;
  private final CuentasBusinessService cuentasBusinessService;

  @Autowired
  public CuentasConsumer(
      CuentasProducer cuentasProducer, CuentasBusinessService cuentasBusinessService) {
    this.cuentasProducer = cuentasProducer;
    this.cuentasBusinessService = cuentasBusinessService;
  }

  @KafkaListener(topics = "ms-personas-to-ms-cuentas", groupId = "group_id")
  public void consumeFromMsPersonas(Object message) {
    try {
      // Convierte el Object a ConsumerRecord
      var consumerRecord = (ConsumerRecord<String, String>) message;

      // Deserializa el valor directamente al record CreateAccountMessage
      var createAccountMessage =
          objectMapper.readValue(consumerRecord.value(), CreateAccountMessage.class);

      log.info("Cuenta a crear: {}", createAccountMessage);

      var response = cuentasBusinessService.createAcocunt(createAccountMessage);

      if (response.get().created()) {
        log.info("Se creo exitosamente la cuenta.");
        cuentasProducer.sendMessage(response);
      } else {
        log.info("Hubo un porblema al crear la cuenta.");
        cuentasProducer.sendMessage(response);
      }

    } catch (Exception e) {
      log.error("Error al consumir el mensaje: {}", e.getMessage());
    }
  }
}
