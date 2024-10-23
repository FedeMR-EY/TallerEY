package ey.com.tarjetas.mstarjetas.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ey.com.tarjetas.mstarjetas.dto.CreateCardMessage;
import ey.com.tarjetas.mstarjetas.service.business.TarjetasBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class TarjetasConsumer {
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final TarjetasBusinessService tarjetasBusinessService;
  private final TarjetasProducer tarjetasProducer;

  @Autowired
  public TarjetasConsumer(TarjetasBusinessService tarjetasBusinessService, TarjetasProducer tarjetasProducer) {
    this.tarjetasBusinessService = tarjetasBusinessService;
    this.tarjetasProducer = tarjetasProducer;
  }

  @KafkaListener(topics = "ms-personas-to-ms-tarjetas", groupId = "group_id")
  public void consumeFromMsPersonas(Object message) throws JsonProcessingException {
    var consumerRecord = (ConsumerRecord<String,String>) message;
    var createCardMessage = objectMapper.readValue(consumerRecord.value(), CreateCardMessage.class);

    log.info("Tarjeta a crear: {}",createCardMessage);

    var response = tarjetasBusinessService.createCard(createCardMessage);

    if (response.get().created()){
      log.info("Tarjeta creada exitosamente");
      tarjetasProducer.sendMessage(response);
    }else {
      log.info("Hubo un problema al intentar crear la tarjeta");
      tarjetasProducer.sendMessage(response);
    }
  }
}
