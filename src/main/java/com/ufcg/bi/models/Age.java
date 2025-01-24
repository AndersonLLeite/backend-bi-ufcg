package com.ufcg.bi.models;
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
public class Age {
 
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

    @ElementCollection
    @CollectionTable(name = "age_distribution", joinColumns = @JoinColumn(name = "age_id"))
    @MapKeyColumn(name = "age_range")
    @Column(name = "count")
    private Map<String, Double> idade;
}
