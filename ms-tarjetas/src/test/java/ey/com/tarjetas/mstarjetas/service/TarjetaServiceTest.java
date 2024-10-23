package ey.com.tarjetas.mstarjetas.service;

import static org.junit.jupiter.api.Assertions.*;

import ey.com.tarjetas.mstarjetas.model.Tarjeta;
import ey.com.tarjetas.mstarjetas.repository.TarjetaRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TarjetaServiceTest {
  @Mock private TarjetaRepository tarjetaRepository;
  @InjectMocks private TarjetaService tarjetaService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {
    Mockito.when(tarjetaRepository.findAll()).thenReturn(new ArrayList<Tarjeta>());
    List<Tarjeta> result = tarjetaService.findAll();
    Assertions.assertNotNull(result);
  }
}
