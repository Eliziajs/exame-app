package com.example.exame_app.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pacientes")
public class Paciente implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column
    private String email;
    @Column(nullable = true,unique = true)
    private String CPF;
    private int idade;
    //@Column(nullable = false)
    private String sexo;
    //private Date dataNascimento;
    private double peso;
    private double altura;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date data = new Date();


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(name = "user_paciente",
    joinColumns = {@JoinColumn (name = "id_user")},
    inverseJoinColumns = {@JoinColumn (name = "id_paciente")})
    private Set<User> users;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)
    private Set<Exame> exames;



}