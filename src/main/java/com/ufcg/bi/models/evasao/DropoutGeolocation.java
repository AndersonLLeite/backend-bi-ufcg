package com.ufcg.bi.models.evasao;

import jakarta.persistence.Id;
import java.util.Map;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DropoutGeolocation {
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
    @CollectionTable(name = "dropout_geolocation_distribution", joinColumns = @JoinColumn(name = "dropout_geolocation_id"))
    @MapKeyColumn(name = "dropout_geolocation")
    @Column(name = "count")
    private Map<String, Double> dropoutGeolocationDistributions;
}
