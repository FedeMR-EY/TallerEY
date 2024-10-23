package ey.com.personas.mspersonas.repository;

import ey.com.personas.mspersonas.model.Usuarios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
  Optional<Usuarios> findByDni(String dni);

  void deleteByDni(String dni);
}
