package com.ufcg.bi.models;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data2 {
    private int codigoDoCurso;
    private String nomeCurso;
    private String status;
    private int codigoDoSetor;
    private String nomeDoSetor;
    private Integer campus;
    private String nomeDoCampus;
    private String periodoAtual; // Atributo original
    private String periodo; // Novo atributo adicionado
    private Map<String, Double> genderDistribution;
    private Map<String, Double> ageDistribution;
    private Map<String, Double> affirmativePolicyDistribution;
   
}
