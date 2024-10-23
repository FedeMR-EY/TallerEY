package ey.com.personas.mspersonas.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonasProducer {
  private static final String TOPIC_MS_CUENTAS = "ms-personas-to-ms-cuentas";
  private static final String TOPIC_MS_TARJETAS = "ms-personas-to-ms-tarjetas";
  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Autowired
  public PersonasProducer(
      @Qualifier(value = "kafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessageToMsCuentas(Object message) {
    kafkaTemplate.send(TOPIC_MS_CUENTAS, message);
  }

  public void sendMessageToMsTarjetas(Object message) {
    kafkaTemplate.send(TOPIC_MS_TARJETAS, message);
  }
}
