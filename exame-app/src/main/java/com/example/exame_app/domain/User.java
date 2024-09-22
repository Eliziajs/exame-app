package com.example.exame_app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String name;
    private String lastName;
    private String genero;
    private String telefone;
    @Column(nullable = true, unique = true)
    private String email;
    private String password;
    @Column()
    private String crm;
    @Column()
    private String cpf;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date data = new Date();


    @ManyToMany (mappedBy = "users", cascade=CascadeType.PERSIST)
    @JsonBackReference
    private List<Paciente> pacientes;

}
