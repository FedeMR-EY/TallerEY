package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.EstadoUsuario;
import ey.com.personas.mspersonas.repository.EstadoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoUsuarioService {
    private final EstadoUsuarioRepository estadoUsuarioRepository;

    @Autowired
    public EstadoUsuarioService(EstadoUsuarioRepository estadoUsuarioRepository) {
        this.estadoUsuarioRepository = estadoUsuarioRepository;
    }

    public List<EstadoUsuario> findAll(){
        return estadoUsuarioRepository.findAll();
    }
}
