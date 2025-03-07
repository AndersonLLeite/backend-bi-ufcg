package com.ufcg.bi.models.TeacherModels;

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
public class Teacher {
    @Id
    @JsonProperty("matricula_do_docente")
    private int teacherId;
    @JsonProperty("codigo_do_setor")
    private int departmentCode;
    @JsonProperty("status")
    private String status;
    @JsonProperty("titulacao")
    private String degree;
}
