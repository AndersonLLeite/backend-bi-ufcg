package com.ufcg.bi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @JsonProperty("codigo_do_curso")
    private int codigoDoCurso;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("status")
    private String status;
    @JsonProperty("codigo_do_setor")
    private int codigoDoSetor;
    @JsonProperty("nome_do_setor")
    private String nomeDoSetor;
    private List<Student> students;
    @JsonProperty("grau_do_curso")
    private String grauDoCurso;
    @JsonProperty("campus")
    private Integer campus;
    @JsonProperty("nome_do_campus")
    private String nomeDoCampus;
    @JsonProperty("turno")
    private String turno;
    @JsonProperty("periodo_de_inicio")
    private String periodoDeInicio;
    @JsonProperty("data_de_funcionamento")
    private String dataDeFuncionamento;
    @JsonProperty("codigo_inep")
    private Integer codigoInep;
    @JsonProperty("modalidade_academica")
    private String modalidadeAcademica;
    @JsonProperty("curriculo_atual")
    private Integer curriculoAtual;
    @JsonProperty("area_de_retencao")
    private Integer areaDeRetencao;
    @JsonProperty("ciclo_enade")
    private Integer cicloEnade;

    List<String> periodos;

    public String getDescricao() {
        return descricao + " (" + codigoDoCurso + ")";
    }

    public String getNomeDoSetor() {
        if (nomeDoSetor != null && nomeDoSetor.contains(" - ")) {
            return nomeDoSetor.split(" - ")[0].trim();
        }
        return nomeDoSetor; // Retorna o nome completo se não houver o separador
    }

}
