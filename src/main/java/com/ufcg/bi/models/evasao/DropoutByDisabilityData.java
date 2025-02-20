package com.ufcg.bi.models.evasao;
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
public class DropoutByDisabilityData {
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
    @CollectionTable(name = "dropout_by_disability", joinColumns = @JoinColumn(name = "dropout_by_disability_data_id"))
    @MapKeyColumn(name = "disability")
    @Column(name = "count")
    private Map<String, Double> dropoutByDisabilitie;

}
