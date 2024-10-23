package ey.com.tarjetas.mstarjetas.repository;

import ey.com.tarjetas.mstarjetas.model.ResumenEmitido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumenEmitidoRepository extends JpaRepository<ResumenEmitido, Integer> {}
