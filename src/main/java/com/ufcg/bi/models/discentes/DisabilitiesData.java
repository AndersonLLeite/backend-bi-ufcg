package com.ufcg.bi.models.discentes;
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
public class DisabilitiesData {
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
    @CollectionTable(name = "disabilities_distribution", joinColumns = @JoinColumn(name = "disabilities_data_id"))
    @MapKeyColumn(name = "disability")
    @Column(name = "count")
    private Map<String, Double> disabilitiesDistribution;
}
