package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.CodigoMoneda;
import ey.com.cuentas.mscuentas.repository.CodigoMonedaRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoMonedaService implements JpaService<Object> {
  private final CodigoMonedaRepository codigoMonedaRepository;

  @Autowired
  public CodigoMonedaService(CodigoMonedaRepository codigoMonedaRepository) {
    this.codigoMonedaRepository = codigoMonedaRepository;
  }

  public List<CodigoMoneda> findAll() {
    return codigoMonedaRepository.findAll();
  }

  @Override
  public Object save(Object entity) {
    return codigoMonedaRepository.save((CodigoMoneda) entity);
  }

  @Override
  public Object findById(Object id) {
    return codigoMonedaRepository
        .findById((Integer) id)
        .orElseThrow(
            () -> new NoSuchElementException("No se encontró la moneda con codigo: " + (id)));
  }
}
