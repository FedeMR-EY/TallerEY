package ey.com.cuentas.mscuentas.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CuentasProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "ms-cuentas-to-ms-personas";

    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
