package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.EstadoCuenta;
import ey.com.cuentas.mscuentas.repository.EstadoCuentaRepository;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoCuentaService implements JpaService<Object> {
  private final EstadoCuentaRepository estadoCuentaRepository;

  @Autowired
  public EstadoCuentaService(EstadoCuentaRepository estadoCuentaRepository) {
    this.estadoCuentaRepository = estadoCuentaRepository;
  }

  public List<EstadoCuenta> findAll() {
    return estadoCuentaRepository.findAll();
  }

  @Override
  public Object save(Object entity) {
    return estadoCuentaRepository.save((EstadoCuenta) entity);
  }

  @Override
  public Object findById(Object id) {
    return estadoCuentaRepository.findById((Integer) id).orElseThrow(() -> new NoSuchElementException("No se encontro el estado para el id: "+(id)));
  }
}
