package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.EstadoCuenta;
import ey.com.cuentas.mscuentas.repository.EstadoCuentaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoCuentaServiceTest {
    @Mock
    private EstadoCuentaRepository estadoCuentaRepository;
    @InjectMocks
    private EstadoCuentaService estadoCuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Mockito.when(estadoCuentaRepository.findAll()).thenReturn(new ArrayList<EstadoCuenta>());
        List<EstadoCuenta> result = estadoCuentaService.findAll();
        Assertions.assertNotNull(result);
    }
}