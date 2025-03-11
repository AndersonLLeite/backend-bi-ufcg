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
public class SecondarySchoolType {
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
    @CollectionTable(name = "secondary_school_type_data", joinColumns = @JoinColumn(name = "secondary_school_type_id"))
    @MapKeyColumn(name = "secondary_school")
    @Column(name = "count")
    private Map<String, Double> secondarySchoolType;
}
