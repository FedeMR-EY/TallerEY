package ey.com.cuentas.mscuentas.repository;

import ey.com.cuentas.mscuentas.model.CodigoMoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoMonedaRepository extends JpaRepository<CodigoMoneda, Integer> {}
