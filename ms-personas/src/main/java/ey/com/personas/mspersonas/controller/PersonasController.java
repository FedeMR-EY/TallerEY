package ey.com.personas.mspersonas.controller;


import ey.com.personas.mspersonas.dto.RegistrarPersonaRequest;
import ey.com.personas.mspersonas.dto.RegistrarPersonaResponse;
import ey.com.personas.mspersonas.service.business.PersonasBusinessService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/taller_ey")
public class PersonasController {

    private final PersonasBusinessService personasBusinessService;

    @Autowired
    public PersonasController(PersonasBusinessService personasBusinessService) {
        this.personasBusinessService = personasBusinessService;
    }

    @PostMapping(value = "/personas")
    public ResponseEntity<RegistrarPersonaResponse> registrarPersona(
            @Valid @RequestBody RegistrarPersonaRequest registrarPersonaRequest, BindingResult result) {
        return personasBusinessService.registrarPersona(registrarPersonaRequest, result);
    }
}
