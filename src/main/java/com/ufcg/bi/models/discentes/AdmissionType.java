package com.ufcg.bi.models.discentes;
import java.util.Map;

import com.ufcg.bi.utils.Utils;

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
public class AdmissionType {
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
    @CollectionTable(name = "admission_type_distribution", joinColumns = @JoinColumn(name = "admission_type_id"))
    @MapKeyColumn(name = "admission_type")
    @Column(name = "count")
    private Map<String, Double> admissionTypeDistribution;

}
