package com.ufcg.bi.models.campus;

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
public class StudentStatusDistribution {
    
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
    @CollectionTable(name = "students_status", joinColumns = @JoinColumn(name = "student_status_id"))
    @MapKeyColumn(name = "student_status_count")
    @Column(name = "count")
    private Map<String, Double> studentStatus;
}
