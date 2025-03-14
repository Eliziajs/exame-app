package com.example.exame_app.services;


import com.example.exame_app.domain.Exame;
import com.example.exame_app.repositories.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    ExameRepository repository;

    public Optional<List<Exame>> findAll(){
        return Optional.of(repository.findAll());

    }
    public Optional<Exame>findById(Long id){
        return repository.findById(id);
    }

    public Exame create(Exame e){
        return repository.save(e);
    }
    public Exame update(Exame e){
        return repository.save(e);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }


}
