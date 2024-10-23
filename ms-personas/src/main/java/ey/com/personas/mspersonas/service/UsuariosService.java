package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.Usuarios;
import ey.com.personas.mspersonas.repository.UsuariosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService implements JpaService<Object> {
  private final UsuariosRepository usuariosRepository;

  @Autowired
  public UsuariosService(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
  }

  public List<Usuarios> findAll() {
    return usuariosRepository.findAll();
  }

  @Override
  public Object save(Object entity) {
    return usuariosRepository.save((Usuarios) entity).getPersnum();
  }

  @Override
  public Object findById(Object id) {
    return null;
  }

  public Optional<Usuarios> findByDni(String dni) {
    return usuariosRepository.findByDni(dni);
  }

  public void deleteByDni(String dni) {
    usuariosRepository.deleteByDni(dni);
  }
}
