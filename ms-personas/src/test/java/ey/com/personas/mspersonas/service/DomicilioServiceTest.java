package ey.com.personas.mspersonas.service;

import static org.junit.jupiter.api.Assertions.*;

import ey.com.personas.mspersonas.model.Domicilio;
import ey.com.personas.mspersonas.repository.DomicilioRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DomicilioServiceTest {
  @Mock private DomicilioRepository domicilioRepository;
  @InjectMocks private DomicilioService domicilioService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {
    Mockito.when(domicilioRepository.findAll()).thenReturn(new ArrayList<Domicilio>());
    List<Domicilio> result = domicilioService.findAll();
    Assertions.assertNotNull(result);
  }
}
