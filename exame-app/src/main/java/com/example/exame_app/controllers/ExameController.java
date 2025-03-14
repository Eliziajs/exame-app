package com.example.exame_app.controllers;


import com.example.exame_app.config.serialization.converter.MediaType;
import com.example.exame_app.domain.Exame;
import com.example.exame_app.dto.RegistroExameDTO;
import com.example.exame_app.repositories.ExameRepository;
import com.example.exame_app.services.ExameService;
import com.example.exame_app.services.RegistroExameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@CrossOrigin
@RestController
@RequestMapping("/exame")
@Tag(name = "cardiological exams", description= "Endpoints for Managing cardiological exams")
public class ExameController {

    private static final String ID = "/{id}";

    @Autowired
    ExameService service;
    @Autowired
    ExameRepository repository;

    @Autowired
    RegistroExameService registroService;



    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_YML})
    @Operation(summary="list exams", description="list all exams", tags={"Exam"},
    responses = {@ApiResponse(description = "Success", responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Exame.class))
                    )
            }),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
    )
    public ResponseEntity<Optional<List<Exame>>> findAll(){
        List<Exame> list = repository.findAll();
        if (!list.isEmpty()) {
            for(Exame exame: list){
                Long id = exame.getId();
                exame.add(linkTo(methodOn(ExameController.class).getById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok().body(service.findAll());
    }




    @GetMapping(value = ID,  produces = {MediaType.APPLICATION_JSON
           , MediaType.APPLICATION_YML})
    @Operation(summary = "Find an exam", description = "Return one exam",
            tags = {"Exam"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = Exame.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Optional<Exame> exame = service.findById(id);
        if (exame.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("exam not found");
        }
        exame.get().add(linkTo(methodOn(ExameController.class).findAll()).withSelfRel());
        return ResponseEntity.ok().body(exame.get());
    }




    @PostMapping( consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Add an exam", description = "Add an exam",
            tags = {"Exam"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = Exame.class))
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Exame> create(@RequestBody RegistroExameDTO e){

        return ResponseEntity.status(HttpStatus.CREATED).body(registroService.novoExame(e));
       /** return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(e));**/
    }


    @PutMapping(value = ID,consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update an exam", description = "Update a exam",
            tags = {"Exam"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = Exame.class))
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object>  update (@RequestBody Exame exame, @PathVariable Long id){
        Optional<Exame> exam = service.findById(id);
        if(exam.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found");
        }
        return ResponseEntity.ok(service.update(exame));
    }




    @DeleteMapping(value = ID)
    @Operation(summary = "Delete an exam", description = "Delete an exam",
            tags = {"Exam"},
            responses = {@ApiResponse(description = "No Content", responseCode = "204",
                    content = @Content
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Exame>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}



