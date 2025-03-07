package com.ufcg.bi.models.campusModels;

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
public class StudentCenterDistribution {

    @Id
    private String id;
    private int codigoDoCurso;
    private String nomeCurso;
    private String status;
    private int codigoDoSetor;
    private String nomeDoSetor;
    private Integer codigoDoCampus;
    private String nomeDoCampus;
    private String periodo;
    private int ano;

    @ElementCollection
    @CollectionTable(name = "student_center_distribution_centers", joinColumns = @JoinColumn(name = "student_center_id"))
    @MapKeyColumn(name = "student_center_count")
    @Column(name = "count")
    private Map<String, Double> centro;
    
}