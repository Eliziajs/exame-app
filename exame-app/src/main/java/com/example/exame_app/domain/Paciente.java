package com.example.exame_app.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pacientes")
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(nullable = false)
    private String nome;
    //@Column(nullable = false)
    private String email;
    //private String CPF;
    private int idade;
    //@Column(nullable = false)
    private String sexo;
    //private Date dataNascimento;
    private double peso;
    private double altura;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date data = new Date();
    /**private double ao;
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
    private String comentario;**/


    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "user_paciente",
    joinColumns = {@JoinColumn (name = "id_user")},
    inverseJoinColumns = {@JoinColumn (name = "id_paciente")}
    )
    @JsonBackReference
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER)
    private List<Exame> exames;



}