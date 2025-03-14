package com.example.exame_app.dto;

import com.example.exame_app.domain.Exame;
import com.example.exame_app.domain.Paciente;

import java.util.Set;

public record RegistroExameDTO(Long pacienteId, Exame exame) {
}
