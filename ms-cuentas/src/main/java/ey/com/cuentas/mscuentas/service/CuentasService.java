package ey.com.cuentas.mscuentas.service;

import ey.com.cuentas.mscuentas.model.Cuentas;
import ey.com.cuentas.mscuentas.repository.CuentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentasService {
    private final CuentasRepository cuentasRepository;

    @Autowired
    public CuentasService(CuentasRepository cuentasRepository) {
        this.cuentasRepository = cuentasRepository;
    }

    public List<Cuentas> findAll(){
        return cuentasRepository.findAll();
    }
}
