package com.example.exame_app.controllers;


import com.example.exame_app.domain.Paciente;
import com.example.exame_app.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @GetMapping()
    public ResponseEntity<List<Paciente>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<Paciente>> GetbyId (@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @PostMapping("")
    public ResponseEntity<Paciente> newObj(@RequestBody Paciente newUser){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(newUser));
    }
   @PutMapping("{id}")
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
    @DeleteMapping("{id}")
    public ResponseEntity<Paciente> deletePaciente (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
