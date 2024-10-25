package ey.com.cuentas.mscuentas.service.business;

import ey.com.cuentas.mscuentas.dto.CreateAccountMessage;
import ey.com.cuentas.mscuentas.dto.CreateAccountMessageResponse;
import ey.com.cuentas.mscuentas.model.CodigoMoneda;
import ey.com.cuentas.mscuentas.model.Cuentas;
import ey.com.cuentas.mscuentas.model.EstadoCuenta;
import ey.com.cuentas.mscuentas.service.CodigoMonedaService;
import ey.com.cuentas.mscuentas.service.CuentasService;
import ey.com.cuentas.mscuentas.service.EstadoCuentaService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CuentasBusinessService {
  private final CuentasService cuentasService;
  private final CodigoMonedaService codigoMonedaService;
  private final EstadoCuentaService estadoCuentaService;

  @Autowired
  public CuentasBusinessService(
      CuentasService cuentasService,
      CodigoMonedaService codigoMonedaService,
      EstadoCuentaService estadoCuentaService) {
    this.cuentasService = cuentasService;
    this.codigoMonedaService = codigoMonedaService;
    this.estadoCuentaService = estadoCuentaService;
  }

  public Optional<CreateAccountMessageResponse> createAcocunt(
      CreateAccountMessage createAccountMessage) {
    try {
      switch (createAccountMessage.accountType()) {
        case BASIC -> {
          var cuentaPesos = new Cuentas();
          var codigoMoneda = (Optional<CodigoMoneda>) codigoMonedaService.findById(6);
          var estadoCuenta = (Optional<EstadoCuenta>) estadoCuentaService.findById(1);
          var randomNumber = new Random().nextInt(9000000);

          log.info("codigoMoneda: {}", codigoMoneda);
          log.info("estadoCuenta: {}", estadoCuenta);

          cuentaPesos.setDivisa(codigoMoneda.get());
          cuentaPesos.setSaldo(BigDecimal.valueOf(createAccountMessage.request().sueldoBruto()));
          cuentaPesos.setEstado(estadoCuenta.get());
          cuentaPesos.setPersnum(createAccountMessage.personNumber());
          cuentaPesos.setNumcuenta(String.valueOf(randomNumber));

          String numCuenta = (String) cuentasService.save(cuentaPesos);
          return Optional.of(
              new CreateAccountMessageResponse(
                  createAccountMessage.uuid(), true, Collections.singletonList(numCuenta)));
        }
        case BASIC_PLUS_CARD, PREMIUM_GOLD, PREMIUM_BLACK -> {
          var cuentaPesos = new Cuentas();
          var codigoMoneda = (Optional<CodigoMoneda>) codigoMonedaService.findById(6);
          var estadoCuenta = (Optional<EstadoCuenta>) estadoCuentaService.findById(1);
          var randomNumber = new Random().nextInt(9000000);

          cuentaPesos.setDivisa(codigoMoneda.get());
          cuentaPesos.setSaldo(BigDecimal.valueOf(createAccountMessage.request().sueldoBruto()));
          cuentaPesos.setEstado(estadoCuenta.get());
          cuentaPesos.setPersnum(createAccountMessage.personNumber());
          cuentaPesos.setNumcuenta(String.valueOf(randomNumber));

          var numCuentaPesos = (String) cuentasService.save(cuentaPesos);

          var cuentaDolares = new Cuentas();
          var codigoMonedaDolar = (Optional<CodigoMoneda>) codigoMonedaService.findById(1);
          var estadoCuentaDolar = (Optional<EstadoCuenta>) estadoCuentaService.findById(1);
          var randomNumberDolar = new Random().nextInt(90000000);

          cuentaDolares.setDivisa(codigoMonedaDolar.get());
          cuentaDolares.setSaldo(BigDecimal.valueOf(createAccountMessage.request().sueldoBruto()));
          cuentaDolares.setEstado(estadoCuentaDolar.get());
          cuentaDolares.setPersnum(createAccountMessage.personNumber());
          cuentaDolares.setNumcuenta(String.valueOf(randomNumberDolar));

          var numCuentaDolares = (String) cuentasService.save(cuentaDolares);

          return Optional.of(
              new CreateAccountMessageResponse(
                  createAccountMessage.uuid(), true, List.of(numCuentaPesos, numCuentaDolares)));
        }
      }
    } catch (Exception e) {
      log.error("Ocurrio un error al crear una cuenta: {}", (Object) e);
      return Optional.of(
          new CreateAccountMessageResponse(createAccountMessage.uuid(), false, null));
    }
    return Optional.of(new CreateAccountMessageResponse(createAccountMessage.uuid(), false, null));
  }
}
