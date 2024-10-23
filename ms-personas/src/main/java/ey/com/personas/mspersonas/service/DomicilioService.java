package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.Domicilio;
import ey.com.personas.mspersonas.repository.DomicilioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioService implements JpaService<Object> {
  private final DomicilioRepository domicilioRepository;

  @Autowired
  public DomicilioService(DomicilioRepository domicilioRepository) {
    this.domicilioRepository = domicilioRepository;
  }

  public List<Domicilio> findAll() {
    return domicilioRepository.findAll();
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
