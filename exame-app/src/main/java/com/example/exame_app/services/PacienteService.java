package com.example.exame_app.services;


import com.example.exame_app.domain.Paciente;
import com.example.exame_app.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Optional<Paciente>findById (Long id){
        return pacienteRepository.findById(id);

    }
    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }
    public Paciente create(Paciente p){
        return pacienteRepository.save(p);
    }
    public Paciente update(Paciente p){
        return pacienteRepository.save(p);
    }
    public void delete(Long id){
       pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> findPacienteByCPF(String email){
        return pacienteRepository.findPacienteByCPF(email);

    }
    public Optional<Object> RegistraPaciente(Paciente p){
        if(pacienteRepository.findById(p.getId()).isPresent()){
            return Optional.of("Paciente Already Exist");
        }
        return Optional.of(pacienteRepository.save(p));
    }
    public Optional<Object> BuscaCPF(String cpf){
        if(pacienteRepository.findPacienteByCPF(cpf).isPresent()){
            return Optional.of("Paciente Already Exist");
        }
        return Optional.of(pacienteRepository.findPacienteByCPF(cpf).get());
    }
}
