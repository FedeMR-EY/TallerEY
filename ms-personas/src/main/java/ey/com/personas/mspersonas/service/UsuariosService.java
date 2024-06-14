package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.Usuarios;
import ey.com.personas.mspersonas.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {
    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuarios> findAll(){
        return usuariosRepository.findAll();
    }
}
