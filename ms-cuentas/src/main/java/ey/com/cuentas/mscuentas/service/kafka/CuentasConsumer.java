package ey.com.cuentas.mscuentas.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CuentasConsumer {
    @KafkaListener(topics = "ms-personas-to-ms-cuentas", groupId = "group_id")
    public void consume(Object message) {

        log.info("Cuenta a crear: {}",message);
    }
}
