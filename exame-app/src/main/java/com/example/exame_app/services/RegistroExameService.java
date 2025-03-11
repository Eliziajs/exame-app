package com.example.exame_app.services;

import com.example.exame_app.domain.Exame;
import com.example.exame_app.dto.RegistroExameDTO;
import com.example.exame_app.repositories.ExameRepository;
import com.example.exame_app.repositories.PacienteRepository;
import com.example.exame_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class RegistroExame {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    ExameRepository exameRepository;

    public RegistroExame(PacienteRepository pacienteRepository, ExameRepository exameRepository, UserRepository userRepository) {
        this.pacienteRepository = pacienteRepository;
        this.exameRepository = exameRepository;
        this.userRepository = userRepository;
    }

    @Transient
    public Exame novoExame(RegistroExameDTO registroExameDTO) {
        Exame e =new Exame();
        e.setPaciente(pacienteRepository.findAllById(registroExameDTO.pacienteId()).stream().findFirst().get());
        return exameRepository.save(e);
    }
}

