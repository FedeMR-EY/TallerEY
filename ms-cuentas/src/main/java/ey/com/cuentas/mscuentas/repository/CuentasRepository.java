package ey.com.cuentas.mscuentas.repository;

import ey.com.cuentas.mscuentas.model.Cuentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentasRepository extends JpaRepository<Cuentas,String> {
}
