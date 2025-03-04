package com.ufcg.bi.models.Internship;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Internship {


    @Id
    @JsonProperty("id")
    private int id;
    @JsonProperty("id_concedente")
    private int idConcedente;
    @JsonProperty("uf_concedente")
    private String ufConcedente;
    @JsonProperty("departamento")
    private String departamento;
    @JsonProperty("obrigatorio")
    private boolean obrigatorio;
    @JsonProperty("status")
    private String status;
    @JsonProperty("bolsa_mensal")
    private int bolsaMensal;
    @JsonProperty("auxilio_transporte_diario")
    private int auxilioTransporteDiario;
    private String razao_social;
    private String municipio;
    private boolean orgao_administracao_publica;

   
}
