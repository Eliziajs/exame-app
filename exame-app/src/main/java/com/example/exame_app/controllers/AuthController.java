package com.example.exame_app.controllers;


import com.example.exame_app.domain.User;
import com.example.exame_app.dto.LoginRequestDTO;
import com.example.exame_app.dto.RegisterRequestDTO;
import com.example.exame_app.dto.ResponseDTO;
import com.example.exame_app.infra.security.TokenService;
import com.example.exame_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
     @Autowired
     UserRepository repository;
     @Autowired
     PasswordEncoder passwordEncoder;
     @Autowired
     TokenService tokenService;

    @GetMapping("user")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Optional<User>>findById(@PathVariable Long id){
        return ResponseEntity.ok().body(repository.findById(id));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            return ResponseEntity.ok(new ResponseDTO(user.getId(),user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setGenero(body.genero());
            newUser.setLastName(body.lastName());
            newUser.setTelefone(body.telefone());
            newUser.setCpf(body.cpf());
            newUser.setCrm(body.crm());
            //newUser.setDataNascimento(body.dataNascimento());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getId(), newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> update (@RequestBody User u, @PathVariable Long id){
        return repository.findById(id)
                .map(user -> {
                    user.setName(u.getName());
                    user.setLastName(u.getLastName());
                    user.setCpf(u.getCpf());
                    user.setCrm(u.getCrm());
                    user.setPassword(passwordEncoder.encode(u.getPassword()));
                    user.setTelefone(u.getTelefone());
                    user.setGenero(u.getGenero());
                    user.setEmail(u.getEmail());
                    User userUp = repository.save(user);
                    return ResponseEntity.status(HttpStatus.OK).body(userUp);
                })
                .orElse(ResponseEntity.notFound().build());

    }
    @DeleteMapping("user/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
