package com.example.exame_app.dto;

import java.util.Date;

public record RegisterRequestDTO (
        String name,
        String lastName,
        String genero,
        String telefone,
        String email,
        String password,
        String cpf,
        String crm,
        Date data
       ) {
}

