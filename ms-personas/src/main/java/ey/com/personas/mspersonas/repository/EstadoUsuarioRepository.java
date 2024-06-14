package ey.com.personas.mspersonas.repository;

import ey.com.personas.mspersonas.model.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuario,Integer> {
}
