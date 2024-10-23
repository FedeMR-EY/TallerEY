package ey.com.tarjetas.mstarjetas.service.business;

import ey.com.tarjetas.mstarjetas.dto.CreateCardMessage;
import ey.com.tarjetas.mstarjetas.dto.CreateCardMessageResponse;
import ey.com.tarjetas.mstarjetas.model.EstadoTarjeta;
import ey.com.tarjetas.mstarjetas.model.Tarjeta;
import ey.com.tarjetas.mstarjetas.service.TarjetaService;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TarjetasBusinessService {
  private final TarjetaService tarjetaService;

  @Autowired
  public TarjetasBusinessService(TarjetaService tarjetaService) {
    this.tarjetaService = tarjetaService;
  }

  public Optional<CreateCardMessageResponse> createCard(CreateCardMessage message) {

    try {

      switch (message.accountTypes()) {
        case BASIC_PLUS_CARD, PREMIUM_GOLD, PREMIUM_BLACK -> {
          var tarjeta = new Tarjeta();
          var estadoTarjeta = new EstadoTarjeta();

          estadoTarjeta.setDetalle("Activa");

          tarjeta.setPin(new Random().nextInt(9999));
          tarjeta.setNumcuenta(new Random().nextInt(999999));
          tarjeta.setTipo("C");
          tarjeta.setNumtarj(String.valueOf(new Random().nextInt(99999999)));
          tarjeta.setFEmision(new Date().toString());
          tarjeta.setFVencimiento(LocalDate.now().plusYears(3).toString());
          tarjeta.setEstado(estadoTarjeta);

          tarjetaService.save(tarjeta);

          return Optional.of(new CreateCardMessageResponse(message.uuid(), true));
        }
      }

    } catch (Exception e) {
      return Optional.of(new CreateCardMessageResponse(message.uuid(), false));
    }

    return Optional.of(new CreateCardMessageResponse(message.uuid(), false));
  }
}
