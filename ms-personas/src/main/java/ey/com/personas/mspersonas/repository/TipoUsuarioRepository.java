package ey.com.personas.mspersonas.repository;

import ey.com.personas.mspersonas.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario,Integer> {
}
