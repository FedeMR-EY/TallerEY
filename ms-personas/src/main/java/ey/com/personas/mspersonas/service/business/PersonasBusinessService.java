package ey.com.personas.mspersonas.service.business;

import ey.com.personas.mspersonas.dto.RegistrarPersonaRequest;
import ey.com.personas.mspersonas.dto.RegistrarPersonaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class PersonasBusinessService {
    public ResponseEntity<RegistrarPersonaResponse> registrarPersona(RegistrarPersonaRequest request, BindingResult result){
        return null;
    }
}
