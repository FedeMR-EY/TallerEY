package ey.com.cuentas.mscuentas.repository;

import ey.com.cuentas.mscuentas.model.EstadoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCuentaRepository extends JpaRepository<EstadoCuenta, Integer> {
}
