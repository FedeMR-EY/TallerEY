package ey.com.cuentas.mscuentas.service.business;

import ey.com.cuentas.mscuentas.dto.CreateAccountMessage;
import ey.com.cuentas.mscuentas.dto.CreateAccountMessageResponse;
import ey.com.cuentas.mscuentas.model.CodigoMoneda;
import ey.com.cuentas.mscuentas.model.Cuentas;
import ey.com.cuentas.mscuentas.model.EstadoCuenta;
import ey.com.cuentas.mscuentas.service.CuentasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class CuentasBusinessService {
    private final CuentasService cuentasService;
    @Autowired
    public CuentasBusinessService(CuentasService cuentasService) {
        this.cuentasService = cuentasService;
    }
    public Optional<CreateAccountMessageResponse> createAcocunt(CreateAccountMessage createAccountMessage){
        try {
            switch (createAccountMessage.accountType()){
                case BASIC -> {
                    var cuentaPesos = new Cuentas();
                    var codigoMoneda = new CodigoMoneda();
                    var estadoCuenta = new EstadoCuenta();
                    var randomNumber = new Random().nextInt(9000000);

                    codigoMoneda.setSimbolo("ARS");
                    codigoMoneda.setPais("Argentina");

                    estadoCuenta.setDetalle("Activa");

                    cuentaPesos.setDivisa(codigoMoneda);
                    cuentaPesos.setSaldo(BigDecimal.valueOf(createAccountMessage.request().sueldoBruto()));
                    cuentaPesos.setEstado(estadoCuenta);
                    cuentaPesos.setPersnum(createAccountMessage.personNumber());
                    cuentaPesos.setNumcuenta(String.valueOf(randomNumber));

                    String numCuenta = (String) cuentasService.save(cuentaPesos);
                    return Optional.of(new CreateAccountMessageResponse(createAccountMessage.uuid(), true, Collections.singletonList(numCuenta)));
                }
                case BASIC_PLUS_CARD, PREMIUM_GOLD, PREMIUM_BLACK -> {
                    var cuentaPesos = new Cuentas();
                    var codigoMoneda = new CodigoMoneda();
                    var estadoCuenta = new EstadoCuenta();
                    var randomNumber = new Random().nextInt(9000000);

                    codigoMoneda.setSimbolo("ARS");
                    codigoMoneda.setPais("Argentina");

                    estadoCuenta.setDetalle("Activa");

                    cuentaPesos.setDivisa(codigoMoneda);
                    cuentaPesos.setSaldo(BigDecimal.valueOf(createAccountMessage.request().sueldoBruto()));
                    cuentaPesos.setEstado(estadoCuenta);
                    cuentaPesos.setPersnum(createAccountMessage.personNumber());
                    cuentaPesos.setNumcuenta(String.valueOf(randomNumber));

                    var numCuentaPesos = (String) cuentasService.save(cuentaPesos);

                    var cuentaDolares = new Cuentas();
                    var codigoMonedaDolar = new CodigoMoneda();
                    var estadoCuentaDolar = new EstadoCuenta();
                    var randomNumberDolar = new Random().nextInt(90000000);

                    codigoMonedaDolar.setSimbolo("USD");
                    codigoMonedaDolar.setPais("Estados Unidos");

                    estadoCuentaDolar.setDetalle("Activa");

                    cuentaDolares.setDivisa(codigoMoneda);
                    cuentaDolares.setSaldo(BigDecimal.valueOf(createAccountMessage.request().sueldoBruto()));
                    cuentaDolares.setEstado(estadoCuenta);
                    cuentaDolares.setPersnum(createAccountMessage.personNumber());
                    cuentaDolares.setNumcuenta(String.valueOf(randomNumberDolar));

                    var numCuentaDolares = (String) cuentasService.save(cuentaDolares);

                    return Optional.of(new CreateAccountMessageResponse(createAccountMessage.uuid(), true, List.of(numCuentaPesos,numCuentaDolares)));
                }
            }
        }catch (Exception e){
            return Optional.of(new CreateAccountMessageResponse(createAccountMessage.uuid(), false, null));
        }
        return Optional.of(new CreateAccountMessageResponse(createAccountMessage.uuid(), false, null));
    }
}