package ey.com.personas.mspersonas.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonasProducer {
    private static final String TOPIC = "ms-personas-to-ms-cuentas";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    public PersonasProducer(@Qualifier(value = "kafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
