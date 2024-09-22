package com.example.exame_app.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

public record PacienteDTO(
         Long id,
         String nome,

         String email,
         int idade,
         String sexo,

         Date dataNascimento,
         double peso,
         double altura,
         Date data
){}

