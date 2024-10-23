package ey.com.personas.mspersonas.service;

import static org.junit.jupiter.api.Assertions.*;

import ey.com.personas.mspersonas.model.Usuarios;
import ey.com.personas.mspersonas.repository.UsuariosRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class UsuariosServiceTest {
  @Mock private UsuariosRepository usuariosRepository;
  @InjectMocks private UsuariosService usuariosService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findAll() {
    Mockito.when(usuariosRepository.findAll()).thenReturn(new ArrayList<Usuarios>());
    List<Usuarios> result = usuariosService.findAll();
    Assertions.assertNotNull(result);
  }
}
