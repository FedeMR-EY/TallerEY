package ey.com.cuentas.mscuentas.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CuentasProducer {
  private static final String TOPIC = "ms-cuentas-to-ms-personas";
  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Autowired
  public CuentasProducer(
      @Qualifier(value = "kafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(Object message) {
    kafkaTemplate.send(TOPIC, message);
  }
}
