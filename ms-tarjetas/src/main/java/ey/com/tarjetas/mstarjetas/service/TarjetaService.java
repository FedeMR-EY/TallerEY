package ey.com.tarjetas.mstarjetas.service;

import ey.com.tarjetas.mstarjetas.model.Tarjeta;
import ey.com.tarjetas.mstarjetas.repository.TarjetaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaService implements JpaService<Object> {
  private final TarjetaRepository tarjetaRepository;

  @Autowired
  public TarjetaService(TarjetaRepository tarjetaRepository) {
    this.tarjetaRepository = tarjetaRepository;
  }

  public List<Tarjeta> findAll() {
    return tarjetaRepository.findAll();
  }

  @Override
  public Object save(Object entity) {
    return tarjetaRepository.save((Tarjeta) entity);
  }

  @Override
  public Object findById(Object id) {
    return null;
  }
}
