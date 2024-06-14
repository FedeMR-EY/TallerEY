package ey.com.personas.mspersonas.service;

import ey.com.personas.mspersonas.model.Domicilio;
import ey.com.personas.mspersonas.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {
    private final DomicilioRepository domicilioRepository;

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public List<Domicilio> findAll(){
        return domicilioRepository.findAll();
    }
}
