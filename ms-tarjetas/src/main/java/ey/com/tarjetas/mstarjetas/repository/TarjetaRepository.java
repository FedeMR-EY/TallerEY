package ey.com.tarjetas.mstarjetas.repository;

import ey.com.tarjetas.mstarjetas.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {}
