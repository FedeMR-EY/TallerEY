package ey.com.tarjetas.mstarjetas.service;

import ey.com.tarjetas.mstarjetas.model.ResumenEmitido;
import ey.com.tarjetas.mstarjetas.repository.ResumenEmitidoRepository;
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

class ResumenEmitidoServiceTest {
    @Mock
    private ResumenEmitidoRepository resumenEmitidoRepository;
    @InjectMocks
    private ResumenEmitidoService resumenEmitidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Mockito.when(resumenEmitidoRepository.findAll()).thenReturn(new ArrayList<ResumenEmitido>());
        List<ResumenEmitido> result = resumenEmitidoService.findAll();
        Assertions.assertNotNull(result);
    }
}