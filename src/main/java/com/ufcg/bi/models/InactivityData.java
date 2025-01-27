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
public class InactivityData {
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
    @CollectionTable(name = "inactivity_reason_distribution", joinColumns = @JoinColumn(name = "inactivity_id"))
    @MapKeyColumn(name = "reason")
    @Column(name = "count")
    private Map<String, Double> inactivityReasonDistribution;

}
