package ey.com.tarjetas.mstarjetas.service;

import ey.com.tarjetas.mstarjetas.model.EstadoTarjeta;
import ey.com.tarjetas.mstarjetas.repository.EstadoTarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoTarjetaService {
    private final EstadoTarjetaRepository estadoTarjetaRepository;

    @Autowired
    public EstadoTarjetaService(EstadoTarjetaRepository estadoTarjetaRepository) {
        this.estadoTarjetaRepository = estadoTarjetaRepository;
    }

    public List<EstadoTarjeta> findAll(){
        return estadoTarjetaRepository.findAll();
    }
}
