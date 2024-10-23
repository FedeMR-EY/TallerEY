package ey.com.personas.mspersonas.repository;

import ey.com.personas.mspersonas.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {}
