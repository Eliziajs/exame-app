package com.example.exame_app.controllers;


import com.example.exame_app.domain.Exame;
import com.example.exame_app.dto.ExameDTO1;
import com.example.exame_app.repositories.ExameRepository;
import com.example.exame_app.services.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/exame")
public class ExameController {

    @Autowired
    ExameService service;
    @Autowired
    ExameRepository repository;
    ExameDTO1 exame1 = new ExameDTO1().toExame();

    @GetMapping()
    public ResponseEntity <Optional<List<Exame>>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<Exame>> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @PostMapping(value = "", consumes=APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Exame> create(@RequestBody Exame e){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(e));
    }
    @PutMapping("{id}")
    public ResponseEntity<Exame>  update (@RequestBody Exame e, @PathVariable Long id){
        return service.findById(id)
                .map(medidas -> {
                    medidas.setAo(e.getAo());
                    medidas.setAe(e.getAe());
                    medidas.setVid(e.getVid());
                    medidas.setVed(e.getVed());
                    medidas.setVes(e.getVes());
                    medidas.setSiv(e.getSiv());
                    medidas.setPp(e.getPp());
                    medidas.setFracaoEjecao(e.getFracaoEjecao());
                    medidas.setE(e.getE());
                    medidas.setELinha(e.getELinha());
                    medidas.setMedidasCavitarias(e.getMedidasCavitarias());
                    medidas.setMedidasVE(e.getMedidasVE());
                    medidas.setFuncaoSistolica(e.getFuncaoSistolica());
                    medidas.setContratilidadeSegmentar(e.getContratilidadeSegmentar());
                    medidas.setFuncaoDiastolica(e.getFuncaoDiastolica());
                    medidas.setCavidadesDireita(e.getCavidadesDireita());
                    medidas.setAorta(e.getAorta());
                    medidas.setValvulaAortica(e.getValvulaAortica());
                    medidas.setValvulaMitral(e.getValvulaMitral());
                    medidas.setTricuspide(e.getTricuspide());
                    medidas.setValvulaPulmonar(e.getValvulaPulmonar());
                    medidas.setPericardio(e.getPericardio());
                    medidas.setCava(e.getCavidadesDireita());
                    medidas.setComentario(e.getComentario());
                    Exame ex = service.update(medidas);
                    return ResponseEntity.status(HttpStatus.OK).body(ex);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Exame>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


/**
 * select * from eco.exames a
 * join eco.pacientes b
 * where a.id_paciente=b.id
 */
