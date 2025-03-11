package com.ufcg.bi.DTO.teacherDTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private int teacherId;
    private int codidoDoCentro;
    private String status;
    private String titulacao;
    private int campusCode;
}
