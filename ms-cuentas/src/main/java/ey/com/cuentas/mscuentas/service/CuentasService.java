package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.Cuentas;
import ey.com.cuentas.mscuentas.repository.CuentasRepository;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentasService implements JpaService<Object> {
  private final CuentasRepository cuentasRepository;

  @Autowired
  public CuentasService(CuentasRepository cuentasRepository) {
    this.cuentasRepository = cuentasRepository;
  }

  public List<Cuentas> findAll() {
    return cuentasRepository.findAll();
  }

  @Override
  public Object save(Object entity) {
    return cuentasRepository.save((Cuentas) entity).getNumcuenta();
  }

  @Override
  public Object findById(Object id) {
    return cuentasRepository.findById((String) id).orElseThrow(() -> new NoSuchElementException("No se enccontró la cuenta con id: "+((String) id)));
  }
}
