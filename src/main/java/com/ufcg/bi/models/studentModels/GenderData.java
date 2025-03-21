package com.ufcg.bi.models.studentModels;

import java.util.Map;


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
public class GenderData {
   
    @Id
    private String id;
    private int codigoDoCurso;
    private String curso;
    private String status;
    private int codigoDoSetor;
    private String setor;
    private Integer codigoDoCampus;
    private String campus;
    private String periodo;
    private int ano;
    
    @ElementCollection
    @CollectionTable(name = "gender_distribution", joinColumns = @JoinColumn(name = "gender_id"))
    @MapKeyColumn(name = "gender")
    @Column(name = "count")
    private Map<String, Double> sexo;
}