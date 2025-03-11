package com.ufcg.bi.DTO.subjectDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Integer codigoDaDisciplina;
    private String nome;
    private int codigoDoSetor;
    private String nomeDoSetor;
    private int campus;
    private String nomeDoCampus;
    private String status;
    private String tipoDeComponenteCurricular;
}
