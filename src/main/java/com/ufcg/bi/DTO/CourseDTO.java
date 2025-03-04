package com.ufcg.bi.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private int codigoDoCurso;
    private String descricao;
    private String status;
    private int codigoDoSetor;
    private String nomeDoSetor;
    private String grauDoCurso;
    private Integer campus;
    private String nomeDoCampus;
    private String turno;
    private String modalidadeAcademica;
    private Integer curriculoAtual;

    public CourseDTO(Course course) {
    this.codigoDoCurso = course.getCodigoDoCurso();
    this.descricao = course.getDescricao();
    this.status = course.getStatus();
    this.codigoDoSetor = course.getCodigoDoSetor();
    this.nomeDoSetor = course.getNomeDoSetor();
    this.grauDoCurso = course.getGrauDoCurso();
    this.campus = course.getCampus();
    this.nomeDoCampus = course.getNomeDoCampus();
    this.turno = course.getTurno();
    this.modalidadeAcademica = course.getModalidadeAcademica();
    this.curriculoAtual = course.getCurriculoAtual();
}
}
