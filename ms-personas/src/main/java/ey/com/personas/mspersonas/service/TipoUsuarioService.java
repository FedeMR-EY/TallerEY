package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.TipoUsuario;
import ey.com.personas.mspersonas.repository.TipoUsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioService implements JpaService<Object> {
  private final TipoUsuarioRepository tipoUsuarioRepository;

  @Autowired
  public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
    this.tipoUsuarioRepository = tipoUsuarioRepository;
  }

  public List<TipoUsuario> findAll() {
    return tipoUsuarioRepository.findAll();
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
