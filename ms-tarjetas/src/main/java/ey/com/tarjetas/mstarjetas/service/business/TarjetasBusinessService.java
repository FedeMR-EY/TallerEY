package ey.com.tarjetas.mstarjetas.service.business;

import ey.com.tarjetas.mstarjetas.dto.CreateCardMessage;
import ey.com.tarjetas.mstarjetas.dto.CreateCardMessageResponse;
import ey.com.tarjetas.mstarjetas.model.EstadoTarjeta;
import ey.com.tarjetas.mstarjetas.model.Tarjeta;
import ey.com.tarjetas.mstarjetas.service.EstadoTarjetaService;
import ey.com.tarjetas.mstarjetas.service.TarjetaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TarjetasBusinessService {
  private final TarjetaService tarjetaService;
  private final EstadoTarjetaService estadoTarjetaService;

  @Autowired
  public TarjetasBusinessService(
      TarjetaService tarjetaService, EstadoTarjetaService estadoTarjetaService) {
    this.tarjetaService = tarjetaService;
    this.estadoTarjetaService = estadoTarjetaService;
  }

  public Optional<CreateCardMessageResponse> createCard(CreateCardMessage message) {

    try {

      switch (message.accountTypes()) {
        case BASIC_PLUS_CARD, PREMIUM_GOLD, PREMIUM_BLACK -> {
          var tarjeta = new Tarjeta();
          var estadoTarjeta = (Optional<EstadoTarjeta>) estadoTarjetaService.findById(1);

          tarjeta.setPin(new Random().nextInt(9999));
          tarjeta.setNumcuenta(new Random().nextInt(999999));
          tarjeta.setTipo("C");
          tarjeta.setNumtarj(String.valueOf(new Random().nextInt(99999999)));
          tarjeta.setEstado(estadoTarjeta.get());

          var fechaActual = LocalDate.now();
          var fechaVencimiento = fechaActual.plusYears(3);
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
          var formattedDate = fechaActual.format(formatter);
          var formattedDateVencimiento = fechaVencimiento.format(formatter);

          tarjeta.setFEmision(formattedDate);
          tarjeta.setFVencimiento(formattedDateVencimiento);

          tarjetaService.save(tarjeta);

          return Optional.of(new CreateCardMessageResponse(message.uuid(), true));
        }
      }

    } catch (Exception e) {
      log.error("Ocurrio un error al crear una tarjeta: {}", (Object) e.getStackTrace());
      return Optional.of(new CreateCardMessageResponse(message.uuid(), false));
    }

    return Optional.of(new CreateCardMessageResponse(message.uuid(), false));
  }
}
