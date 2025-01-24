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
public class Policy {
    
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

    @ElementCollection
    @CollectionTable(name = "affirmative_policy_distribution", joinColumns = @JoinColumn(name = "policy_id"))
    @MapKeyColumn(name = "policy")
    @Column(name = "count")
    private Map<String, Double> politicaAfirmativa;
}
