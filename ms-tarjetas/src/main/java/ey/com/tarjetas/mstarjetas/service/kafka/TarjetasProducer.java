package ey.com.tarjetas.mstarjetas.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TarjetasProducer {
    private static final String TOPIC = "ms-tarjetas-to-ms-personas";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    public TarjetasProducer(@Qualifier("kafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
