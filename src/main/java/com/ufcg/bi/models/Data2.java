package com.ufcg.bi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigoDoCurso;
    private String nomeCurso;
    private String status;
    private int codigoDoSetor;
    private String nomeDoSetor;
    private Integer campus;
    private String nomeDoCampus;
    private String periodo;

    @ElementCollection
    @CollectionTable(name = "gender_distribution", joinColumns = @JoinColumn(name = "data_id"))
    @MapKeyColumn(name = "gender")
    @Column(name = "percentage")
    private Map<String, Double> genderDistribution;

    @ElementCollection
    @CollectionTable(name = "age_distribution", joinColumns = @JoinColumn(name = "data_id"))
    @MapKeyColumn(name = "age_range")
    @Column(name = "percentage")
    private Map<String, Double> ageDistribution;

    @ElementCollection
    @CollectionTable(name = "affirmative_policy_distribution", joinColumns = @JoinColumn(name = "data_id"))
    @MapKeyColumn(name = "policy")
    @Column(name = "percentage")
    private Map<String, Double> affirmativePolicyDistribution;
}
