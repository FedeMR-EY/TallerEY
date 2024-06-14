package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.EstadoCuenta;
import ey.com.cuentas.mscuentas.repository.EstadoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCuentaService {
    private final EstadoCuentaRepository estadoCuentaRepository;

    @Autowired
    public EstadoCuentaService(EstadoCuentaRepository estadoCuentaRepository) {
        this.estadoCuentaRepository = estadoCuentaRepository;
    }

    public List<EstadoCuenta> findAll(){
        return estadoCuentaRepository.findAll();
    }
}
