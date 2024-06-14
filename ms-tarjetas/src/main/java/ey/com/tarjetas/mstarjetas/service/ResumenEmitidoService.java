package ey.com.tarjetas.mstarjetas.service;

import ey.com.tarjetas.mstarjetas.model.ResumenEmitido;
import ey.com.tarjetas.mstarjetas.repository.ResumenEmitidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumenEmitidoService {
    private final ResumenEmitidoRepository resumenEmitidoRepository;

    @Autowired
    public ResumenEmitidoService(ResumenEmitidoRepository resumenEmitidoRepository) {
        this.resumenEmitidoRepository = resumenEmitidoRepository;
    }

    public List<ResumenEmitido> findAll(){
        return resumenEmitidoRepository.findAll();
    }
}
