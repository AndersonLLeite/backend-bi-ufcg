package com.ufcg.bi.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_do_curso", nullable = false)
    private int codigoDoCurso;

    @Column(nullable = false)
    private String descricao;

    private String status;

    @Column(name = "codigo_do_setor")
    private int codigoDoSetor;

    @Column(name = "nome_do_setor")
    private String nomeDoSetor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // Evita loop infinito no toString
    @EqualsAndHashCode.Exclude // Exclui da comparação
    private List<Student> students;

    @Column(name = "grau_do_curso")
    private String grauDoCurso;

    private Integer campus;

    @Column(name = "nome_do_campus")
    private String nomeDoCampus;

    private String turno;

    @Column(name = "periodo_de_inicio")
    private String periodoDeInicio;

    @Column(name = "data_de_funcionamento")
    private String dataDeFuncionamento;

    @Column(name = "codigo_inep")
    private Integer codigoInep;

    @Column(name = "modalidade_academica")
    private String modalidadeAcademica;

    @Column(name = "curriculo_atual")
    private Integer curriculoAtual;

    @Column(name = "area_de_retencao")
    private Integer areaDeRetencao;

    @Column(name = "ciclo_enade")
    private Integer cicloEnade;
}
