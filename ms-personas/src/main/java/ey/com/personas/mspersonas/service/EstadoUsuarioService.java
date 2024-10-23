package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.EstadoUsuario;
import ey.com.personas.mspersonas.repository.EstadoUsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoUsuarioService implements JpaService<Object> {
  private final EstadoUsuarioRepository estadoUsuarioRepository;

  @Autowired
  public EstadoUsuarioService(EstadoUsuarioRepository estadoUsuarioRepository) {
    this.estadoUsuarioRepository = estadoUsuarioRepository;
  }

  public List<EstadoUsuario> findAll() {
    return estadoUsuarioRepository.findAll();
  }

  @Override
  public Object save(Object entity) {
    return null;
  }

  @Override
  public Object findById(Object id) {
    return null;
  }
}
