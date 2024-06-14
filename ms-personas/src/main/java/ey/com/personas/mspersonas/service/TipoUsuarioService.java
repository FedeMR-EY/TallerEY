package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.TipoUsuario;
import ey.com.personas.mspersonas.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<TipoUsuario> findAll(){
        return tipoUsuarioRepository.findAll();
    }
}
