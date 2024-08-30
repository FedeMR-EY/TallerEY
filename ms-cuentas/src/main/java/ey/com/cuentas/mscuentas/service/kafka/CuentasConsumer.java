package ey.com.cuentas.mscuentas.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CuentasConsumer {
    @KafkaListener(topics = "ms-personas-to-ms-cuentas", groupId = "group_id")
    public void consume(Object message) {
        // Lógica de validación de datos
        System.out.println("Consumed message: " + message);

        // Después de validar, envía una respuesta de vuelta al servicio A
        // (Se asume que tienes un productor en el servicio B)
        // producer.sendMessage(responseMessage);
    }
}
