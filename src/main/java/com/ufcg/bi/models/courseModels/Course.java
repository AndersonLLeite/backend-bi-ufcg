package com.ufcg.bi.models.courseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.bi.models.studentModels.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Course {
    @Id
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
    @Transient
    private List<Student> students;
    @JsonProperty("grau_do_curso")
    private String grauDoCurso;
    @JsonProperty("campus")
    private Integer campus;
    @JsonProperty("nome_do_campus")
    private String nomeDoCampus;
    @JsonProperty("turno")
    private String turno;
    @JsonProperty("modalidade_academica")
    private String modalidadeAcademica;
    @JsonProperty("curriculo_atual")
    private Integer curriculoAtual;
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
