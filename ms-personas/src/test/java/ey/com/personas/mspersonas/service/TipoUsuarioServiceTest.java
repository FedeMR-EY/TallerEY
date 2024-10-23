package ey.com.personas.mspersonas.service;

import static org.junit.jupiter.api.Assertions.*;

import ey.com.personas.mspersonas.model.TipoUsuario;
import ey.com.personas.mspersonas.repository.TipoUsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TipoUsuarioServiceTest {
  @Mock private TipoUsuarioRepository tipoUsuarioRepository;
  @InjectMocks private TipoUsuarioService tipoUsuarioService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {
    Mockito.when(tipoUsuarioRepository.findAll()).thenReturn(new ArrayList<TipoUsuario>());
    List<TipoUsuario> result = tipoUsuarioService.findAll();
    Assertions.assertNotNull(result);
  }
}
