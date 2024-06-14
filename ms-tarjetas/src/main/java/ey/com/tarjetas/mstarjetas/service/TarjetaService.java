package ey.com.tarjetas.mstarjetas.service;

import ey.com.tarjetas.mstarjetas.model.Tarjeta;
import ey.com.tarjetas.mstarjetas.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaService {
    private final TarjetaRepository tarjetaRepository;

    @Autowired
    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public List<Tarjeta> findAll(){
        return tarjetaRepository.findAll();
    }
}
