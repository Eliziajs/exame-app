package com.example.exame_app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name="exames")
public class Exame extends RepresentationModel<Exame> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double ao;
    private double ae;
    private double vid;
    private double ved;
    private double ves;
    private double siv;
    private double pp;
    private double fracaoEjecao;
    private double e;
    private double eLinha;
    private String medidasCavitarias;
    private String medidasVE;
    private String funcaoSistolica;
    private String contratilidadeSegmentar;
    private String funcaoDiastolica;
    private String cavidadesDireita;
    private String aorta;
    private String valvulaAortica;
    private String valvulaMitral;
    private String tricuspide;
    private String valvulaPulmonar;
    private String pericardio;
    private String cava;
    private String comentario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date data = new Date();


    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn(name = "id_paciente")

    @JsonBackReference
    private Paciente paciente;




}
