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
public class EntrantGeolocation {
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
    @CollectionTable(name = "entrant_geolocation_distribution", joinColumns = @JoinColumn(name = "entrant_geolocation_id"))
    @MapKeyColumn(name = "geolocation")
    @Column(name = "count")
    private Map<String, Double> geolocationDistribution;
}
