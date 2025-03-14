package com.example.exame_app.controllers;


import com.example.exame_app.config.serialization.converter.MediaType;
import com.example.exame_app.domain.User;
import com.example.exame_app.dto.LoginRequestDTO;
import com.example.exame_app.dto.RegisterRequestDTO;
import com.example.exame_app.dto.ResponseDTO;
import com.example.exame_app.infra.security.TokenService;
import com.example.exame_app.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
@Tag(name = "Users", description= "Endpoints for Managing Users")
public class AuthController {

    private static final String ID = "/{id}";

     @Autowired
     UserRepository repository;
     @Autowired
     PasswordEncoder passwordEncoder;
     @Autowired
     TokenService tokenService;



    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all user", description = "Finds all Users",
            tags = {"Users"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(repository.findAll());
    }



    @GetMapping(value = ID, produces = {MediaType.APPLICATION_JSON,
          MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a User", description = "Finds a User",
            tags = {"User"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = User.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Optional<User>>findById(@PathVariable Long id){
        return ResponseEntity.ok().body(repository.findById(id));
    }



    @PostMapping (value= "login", consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Add a User", description = "Add a User",
            tags = {"User"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = LoginRequestDTO.class))
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            return ResponseEntity.ok(new ResponseDTO(user.getId(),user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }





    @PostMapping (value= "register", consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Add a User", description = "Add a User",
            tags = {"User"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = RegisterRequestDTO.class))
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
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





    @PutMapping(value = ID,consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a User", description = "Updates a User",
            tags = {"User"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = User.class))
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
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




    @DeleteMapping(value = ID)
    @Operation(summary = "Deletes a User", description = "Deletes a User",
            tags = {"User"},
            responses = {@ApiResponse(description = "No Content", responseCode = "204",
                    content = @Content
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<User> deleteUser (@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
