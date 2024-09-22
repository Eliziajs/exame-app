package com.example.exame_app.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;


public record ExameDTO (
        Long id,
        double ao,
        double ae,
        double vid,
        double ved,
        double ves,
        double siv,
        double pp,
        double fracaoEjecao,
        double e,
        double eLinha,
        String medidasCavitarias,
        String medidasVE,
        String funcaoSistolica,
        String contratilidadeSegmentar,
        String funcaoDiastolica,
        String cavidadesDireita,
        String aorta,
        String valvulaAortica,
        String valvulaMitral,
        String tricuspide,
        String valvulaPulmonar,
        String pericardio,
        String cava,
        String comentario,
        Date data){}

