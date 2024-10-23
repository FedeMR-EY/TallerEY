package ey.com.tarjetas.mstarjetas.service;

import ey.com.tarjetas.mstarjetas.model.ResumenEmitido;
import ey.com.tarjetas.mstarjetas.repository.ResumenEmitidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumenEmitidoService implements JpaService<Object> {
  private final ResumenEmitidoRepository resumenEmitidoRepository;

  @Autowired
  public ResumenEmitidoService(ResumenEmitidoRepository resumenEmitidoRepository) {
    this.resumenEmitidoRepository = resumenEmitidoRepository;
  }

  public List<ResumenEmitido> findAll() {
    return resumenEmitidoRepository.findAll();
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
