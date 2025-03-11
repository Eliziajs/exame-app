package com.example.exame_app.dto;

import com.example.exame_app.domain.Exame;

import java.util.Set;

public record RegistroExame(String nome, Integer CRM, Set<Long>pacienteId, Exame exame) {
}
