package com.example.exame_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter@Setter
public class ExameDTO1 implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private Date data;

    public ExameDTO1(double ao, double ae, double vid, double ved, double ves, double siv, double pp, double fracaoEjecao, double e, double eLinha, String medidasCavitarias, String medidasVE, String funcaoSistolica, String contratilidadeSegmentar, String funcaoDiastolica, String cavidadesDireita, String aorta, String valvulaAortica, String valvulaMitral, String tricuspide, String valvulaPulmonar, String pericardio, String cava, String comentario, Date data) {
        this.ao = ao;
        this.ae = ae;
        this.vid = vid;
        this.ved = ved;
        this.ves = ves;
        this.siv = siv;
        this.pp = pp;
        this.fracaoEjecao = fracaoEjecao;
        this.e = e;
        this.eLinha = eLinha;
        this.medidasCavitarias = medidasCavitarias;
        this.medidasVE = medidasVE;
        this.funcaoSistolica = funcaoSistolica;
        this.contratilidadeSegmentar = contratilidadeSegmentar;
        this.funcaoDiastolica = funcaoDiastolica;
        this.cavidadesDireita = cavidadesDireita;
        this.aorta = aorta;
        this.valvulaAortica = valvulaAortica;
        this.valvulaMitral = valvulaMitral;
        this.tricuspide = tricuspide;
        this.valvulaPulmonar = valvulaPulmonar;
        this.pericardio = pericardio;
        this.cava = cava;
        this.comentario = comentario;
        this.data = data;
    }

    public ExameDTO1() {

    }

    public ExameDTO1 toExame() {
        ExameDTO1 exame = new ExameDTO1();
        exame.setAo(ao);
        exame.setAe(ae);
        exame.setAorta(aorta);
        return exame;

    }

}
