package ey.com.personas.mspersonas.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PersonasConsumer {
    @KafkaListener(topics = "ms-cuentas-to-ms-personas", groupId = "group_id")
    public void consume(Object message) {
        // Lógica para manejar la respuesta de validación de datos
        System.out.println("Consumed response message: " + message);
        // Continúa con el flujo de creación de la cuenta
    }
}
