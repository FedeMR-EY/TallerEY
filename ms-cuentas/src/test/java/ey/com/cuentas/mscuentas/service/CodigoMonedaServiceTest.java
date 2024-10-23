package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.CodigoMoneda;
import ey.com.cuentas.mscuentas.repository.CodigoMonedaRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CodigoMonedaServiceTest {

  @Mock private CodigoMonedaRepository codigoMonedaRepository;
  @InjectMocks private CodigoMonedaService codigoMonedaService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {
    Mockito.when(codigoMonedaRepository.findAll()).thenReturn(new ArrayList<CodigoMoneda>());
    List<CodigoMoneda> result = codigoMonedaService.findAll();
    Assertions.assertNotNull(result);
  }
}
