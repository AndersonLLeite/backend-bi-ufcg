package com.ufcg.bi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataTeste {

    private int codigoDoCurso;

    private String descricao;

    private String status;

    private int codigoDoSetor;

    private String nomeDoSetor;

    private List<Student> students;

    private String grauDoCurso;

    private Integer campus;

    private String nomeDoCampus;

    private String turno;

    private String periodoDeInicio;

    private String dataDeFuncionamento;

    private Integer codigoInep;

    private String modalidadeAcademica;

    private Integer curriculoAtual;

    private Integer areaDeRetencao;

    private Integer cicloEnade;

    private String periodo;

    // Gender Distribution
    private Double male;
    private Double female;

    // Age Distribution
    private Double age_18_20;
    private Double age_21_25;
    private Double age_26_plus;

    // Status Distribution
    private Double statusActive;
    private Double statusInactive;

}
