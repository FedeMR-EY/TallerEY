package ey.com.tarjetas.mstarjetas.repository;

import ey.com.tarjetas.mstarjetas.model.EstadoTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTarjetaRepository extends JpaRepository<EstadoTarjeta, Integer> {}
