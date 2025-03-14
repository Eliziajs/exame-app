package com.example.exame_app.services;

import com.example.exame_app.domain.Exame;
import com.example.exame_app.dto.RegistroExameDTO;
import com.example.exame_app.repositories.ExameRepository;
import com.example.exame_app.repositories.PacienteRepository;
import com.example.exame_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.stream.Collectors;

@Service
public class RegistroExameService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    ExameRepository exameRepository;

    public RegistroExameService(PacienteRepository pacienteRepository, ExameRepository exameRepository, UserRepository userRepository) {
        this.pacienteRepository = pacienteRepository;
        this.exameRepository = exameRepository;
        this.userRepository = userRepository;
    }

    @Transient
    public Exame novoExame(RegistroExameDTO registroExameDTO) {
        Exame e =new Exame();
        e.setPaciente(pacienteRepository.findById(registroExameDTO.pacienteId()).get());
        return exameRepository.save(e);
    }
}

