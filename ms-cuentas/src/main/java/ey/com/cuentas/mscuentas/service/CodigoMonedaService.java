package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.CodigoMoneda;
import ey.com.cuentas.mscuentas.repository.CodigoMonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodigoMonedaService {
    private final CodigoMonedaRepository codigoMonedaRepository;

    @Autowired
    public CodigoMonedaService(CodigoMonedaRepository codigoMonedaRepository) {
        this.codigoMonedaRepository = codigoMonedaRepository;
    }

    public List<CodigoMoneda> findAll(){
        return codigoMonedaRepository.findAll();
    }
}
