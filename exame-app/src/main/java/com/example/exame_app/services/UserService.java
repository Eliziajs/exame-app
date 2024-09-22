package com.example.exame_app.services;


import com.example.exame_app.domain.User;
import com.example.exame_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User create(User u){
        return userRepository.save(u);
    }
    public User update(User u) {
        return userRepository.save(u);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }


}
