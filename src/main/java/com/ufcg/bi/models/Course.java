package com.ufcg.bi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @JsonProperty("codigo_do_curso")
    @Column(name = "codigo_do_curso", nullable = false, unique = true)
    private int codigoDoCurso;

    @JsonProperty("descricao")
    @Column(nullable = false)
    private String descricao;

    @JsonProperty("status")
    private String status;

    @JsonProperty("codigo_do_setor")
    @Column(name = "codigo_do_setor")
    private int codigoDoSetor;

    @JsonProperty("nome_do_setor")
    @Column(name = "nome_do_setor")
    private String nomeDoSetor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Student> students;

    @JsonProperty("grau_do_curso")
    @Column(name = "grau_do_curso")
    private String grauDoCurso;

    @JsonProperty("campus")
    private Integer campus;

    @JsonProperty("nome_do_campus")
    @Column(name = "nome_do_campus")
    private String nomeDoCampus;

    @JsonProperty("turno")
    private String turno;

    @JsonProperty("periodo_de_inicio")
    @Column(name = "periodo_de_inicio")
    private String periodoDeInicio;

    @JsonProperty("data_de_funcionamento")
    @Column(name = "data_de_funcionamento")
    private String dataDeFuncionamento;

    @JsonProperty("codigo_inep")
    @Column(name = "codigo_inep")
    private Integer codigoInep;

    @JsonProperty("modalidade_academica")
    @Column(name = "modalidade_academica")
    private String modalidadeAcademica;

    @JsonProperty("curriculo_atual")
    @Column(name = "curriculo_atual")
    private Integer curriculoAtual;

    @JsonProperty("area_de_retencao")
    @Column(name = "area_de_retencao")
    private Integer areaDeRetencao;

    @JsonProperty("ciclo_enade")
    @Column(name = "ciclo_enade")
    private Integer cicloEnade;


}
