package com.example.exame_app.controllers;


import com.example.exame_app.config.serialization.converter.MediaType;
import com.example.exame_app.domain.Paciente;
import com.example.exame_app.services.PacienteService;
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

@RestController
@RequestMapping("/paciente")
@Tag(name = "patients", description= "Endpoints for Managing patients")
public class PacienteController {

    private static final String ID = "/{id}";

    @Autowired
    private PacienteService service;



    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all patients", description = "Find all patient",
            tags = {"Patient"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Paciente.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Paciente>> findAll(){

        return ResponseEntity.ok().body(service.findAll());
    }





    @GetMapping(value = ID, produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "Find a patient", description = "Find a patient",
            tags = {"Patient"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = Paciente.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Optional<Paciente>> GetbyId (@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }





    @PostMapping (consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Add a patient", description = "Add a patient",
            tags = {"Patient"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = Paciente.class))
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Paciente> newObj(@RequestBody Paciente newUser){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(newUser));
    }





    @PutMapping(value = ID,consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a patient", description = "Updates a patient",
            tags = {"Patient"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content =
                    @Content(schema = @Schema(implementation = Paciente.class))
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Paciente> replacePaciente(@RequestBody Paciente newUser, @PathVariable Long id){
        return service.findById(id)
                .map(paciente -> {
                    paciente.setNome(newUser.getNome());
                    paciente.setEmail(newUser.getEmail());
                    paciente.setSexo(newUser.getSexo());
                    paciente.setEmail(newUser.getEmail());
                    paciente.setPeso(newUser.getPeso());
                    paciente.setAltura(newUser.getAltura());
                    /**paciente.setMedidasCavitarias(newUser.getMedidasCavitarias());
                    paciente.setMedidasVE(newUser.getMedidasVE());
                    paciente.setFuncaoSistolica(newUser.getFuncaoSistolica());
                    paciente.setContratilidadeSegmentar(newUser.getContratilidadeSegmentar());
                    paciente.setFuncaoDiastolica(newUser.getFuncaoDiastolica());
                    paciente.setCavidadesDireita(newUser.getCavidadesDireita());
                    paciente.setAorta(newUser.getAorta());
                    paciente.setValvulaAortica(newUser.getValvulaAortica());
                    paciente.setValvulaMitral(newUser.getValvulaMitral());
                    paciente.setTricuspide(newUser.getTricuspide());
                    paciente.setValvulaPulmonar(newUser.getValvulaPulmonar());
                    paciente.setPericardio(newUser.getPericardio());
                    paciente.setCava(newUser.getCavidadesDireita());
                    paciente.setComentario(newUser.getComentario());
                    paciente.setAo(newUser.getAo());
                    paciente.setAe(newUser.getAe());
                    paciente.setVid(newUser.getVid());
                    paciente.setVed(newUser.getVed());
                    paciente.setVes(newUser.getVes());
                    paciente.setSiv(newUser.getSiv());
                    paciente.setPp(newUser.getPp());
                    paciente.setFracaoEjecao(newUser.getFracaoEjecao());
                    paciente.setE(newUser.getE());
                    paciente.setELinha(newUser.getELinha());**/
                    // paciente.setDataNascimento(newUser.getDataNascimento());}
                    Paciente p = service.update(paciente);
                    return ResponseEntity.ok().body(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }





    @DeleteMapping(ID)
    @Operation(summary = "Deletes a patient", description = "Deletes a patient",
            tags = {"Patient"},
            responses = {@ApiResponse(description = "No Content", responseCode = "204",
                    content = @Content
            ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Paciente> deletePaciente (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
