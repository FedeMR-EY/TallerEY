package ey.com.personas.mspersonas.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonasProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "ms-personas-to-ms-cuentas";

    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
