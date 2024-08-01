package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.EstadoUsuario;
import ey.com.personas.mspersonas.repository.EstadoUsuarioRepository;
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

class EstadoUsuarioServiceTest {
    @Mock
    private EstadoUsuarioRepository estadoUsuarioRepository;
    @InjectMocks
    private EstadoUsuarioService estadoUsuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Mockito.when(estadoUsuarioRepository.findAll()).thenReturn(new ArrayList<EstadoUsuario>());
        List<EstadoUsuario> result = estadoUsuarioService.findAll();
        Assertions.assertNotNull(result);
    }
}