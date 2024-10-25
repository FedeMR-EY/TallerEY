package ey.com.tarjetas.mstarjetas.service;

import ey.com.tarjetas.mstarjetas.model.EstadoTarjeta;
import ey.com.tarjetas.mstarjetas.repository.EstadoTarjetaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoTarjetaService implements JpaService<Object> {
  private final EstadoTarjetaRepository estadoTarjetaRepository;

  @Autowired
  public EstadoTarjetaService(EstadoTarjetaRepository estadoTarjetaRepository) {
    this.estadoTarjetaRepository = estadoTarjetaRepository;
  }

  public List<EstadoTarjeta> findAll() {
    return estadoTarjetaRepository.findAll();
  }

  @Override
  public Object save(Object entity) {
    return null;
  }

  @Override
  public Object findById(Object id) {
    return estadoTarjetaRepository.findById((Integer) id);
  }
}
