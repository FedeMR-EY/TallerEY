package ey.com.personas.mspersonas.controller;

import ey.com.personas.mspersonas.dto.RegistrarPersonaRequest;
import ey.com.personas.mspersonas.dto.RegistrarPersonaResponse;
import ey.com.personas.mspersonas.model.Usuarios;
import ey.com.personas.mspersonas.service.UsuariosService;
import ey.com.personas.mspersonas.service.business.PersonasBusinessService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/taller_ey")
public class PersonasController {
  private final PersonasBusinessService personasBusinessService;
  private final UsuariosService usuariosService;

  @Autowired
  public PersonasController(
      PersonasBusinessService personasBusinessService, UsuariosService usuariosService) {
    this.personasBusinessService = personasBusinessService;
    this.usuariosService = usuariosService;
  }

  @GetMapping(value = "/personas")
  public ResponseEntity<List<Usuarios>> getAllUsers() {
    List<Usuarios> usuarios = usuariosService.findAll();
    return new ResponseEntity<>(usuarios, HttpStatus.OK);
  }

  @PostMapping(value = "/personas")
  public ResponseEntity<RegistrarPersonaResponse> registrarPersona(
      @Valid @RequestBody RegistrarPersonaRequest registrarPersonaRequest, BindingResult result) {
    return personasBusinessService.registrarPersona(registrarPersonaRequest, result);
  }
}
