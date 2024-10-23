package ey.com.tarjetas.mstarjetas.service;

import static org.junit.jupiter.api.Assertions.*;

import ey.com.tarjetas.mstarjetas.model.EstadoTarjeta;
import ey.com.tarjetas.mstarjetas.repository.EstadoTarjetaRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class EstadoTarjetaServiceTest {
  @Mock private EstadoTarjetaRepository estadoTarjetaRepository;
  @InjectMocks private EstadoTarjetaService estadoTarjetaService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {
    Mockito.when(estadoTarjetaRepository.findAll()).thenReturn(new ArrayList<EstadoTarjeta>());
    List<EstadoTarjeta> result = estadoTarjetaService.findAll();
    Assertions.assertNotNull(result);
  }
}
