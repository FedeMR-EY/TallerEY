package ey.com.cuentas.mscuentas.service;

import static org.junit.jupiter.api.Assertions.*;

import ey.com.cuentas.mscuentas.model.Cuentas;
import ey.com.cuentas.mscuentas.repository.CuentasRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CuentasServiceTest {

  @Mock private CuentasRepository cuentasRepository;
  @InjectMocks private CuentasService cuentasService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {
    Mockito.when(cuentasRepository.findAll()).thenReturn(new ArrayList<Cuentas>());
    List<Cuentas> result = cuentasService.findAll();
    Assertions.assertNotNull(result);
  }
}
