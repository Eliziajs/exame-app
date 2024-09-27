package com.example.exame_app.controllers;

import com.example.exame_app.domain.Exame;
import com.example.exame_app.domain.Paciente;
import com.example.exame_app.repositories.ExameRepository;
import com.example.exame_app.repositories.PacienteRepository;
import com.example.exame_app.services.ExameService;
import com.example.exame_app.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/reg")
public class RegistroPaciente {

    @Autowired
    ExameRepository exameRepository;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    ExameService exameService;

    @PostMapping("/{id}")
   public ResponseEntity<Object> insere (@PathVariable Long id, @RequestBody Exame exame) {
      Optional<Paciente> paciente = pacienteService.findById(id);
      if (paciente.isEmpty()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não en;contrado");
      }
        exame.setPaciente(paciente.get());
       return ResponseEntity.status(HttpStatus.CREATED).body(exameService.create(exame));

   }

}
