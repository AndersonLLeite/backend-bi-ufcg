package com.ufcg.bi.models.subject;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Id;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @JsonProperty("codigo_da_disciplina")
    private Integer codigoDaDisciplina;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("codigo_do_setor")
    private int codigoDoSetor;
    @JsonProperty("nome_do_setor")
    private String nomeDoSetor;
    @JsonProperty("campus")
    private int campus;
    @JsonProperty("nome_do_campus")
    private String nomeDoCampus;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tipo_de_componente_curricular")
    private String tipoDeComponenteCurricular;

    
}
