package com.ufcg.bi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    
    private Course course;
    @JsonProperty("codigo_do_curso")
    private Integer codigoDoCurso;
    @JsonProperty("genero")
    private String genero;
    @JsonProperty("idade")
    private String idade;
    @JsonProperty("situacao")
    private String situacao;
    @JsonProperty("motivo_de_evasao")
    private String motivoDeEvasao;
    @JsonProperty("periodo_de_evasao")
    private String periodoDeEvasao;
    @JsonProperty("forma_de_ingresso")
    private String formaDeIngresso;
    @JsonProperty("periodo_de_ingresso")
    private String periodoDeIngresso;
    @JsonProperty("naturalidade")
    private String naturalidade;
    @JsonProperty("cor")
    private String cor;
    @JsonProperty("deficiencias")
    private List<String> deficiencias;
    @JsonProperty("tipo_de_ensino_medio")
    private String tipoDeEnsinoMedio;
    @JsonProperty("politica_afirmativa")
    private String politicaAfirmativa;
    @JsonProperty("matricula_do_estudante")
    private String matriculaDoEstudante;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("nome_do_curso")
    private String nomeDoCurso;
    @JsonProperty("turno_do_curso")
    private String turnoDoCurso;
    @JsonProperty("campus")
    private Integer campus;
    @JsonProperty("nome_do_campus")
    private String nomeDoCampus;
    @JsonProperty("codigo_do_setor")
    private Integer codigoDoSetor;
    @JsonProperty("nome_do_setor")
    private String nomeDoSetor;
    @JsonProperty("data_de_nascimento")
    private String dataDeNascimento;
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("nacionalidade")
    private String nacionalidade;
    @JsonProperty("local_de_nascimento")
    private String localDeNascimento;
    @JsonProperty("ano_de_conclusao_ensino_medio")
    private Integer anoDeConclusaoEnsinoMedio;
    
}

