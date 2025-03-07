package com.ufcg.bi.models.dropoutModels;
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
public class DropoutByAdmissionTypeData {
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
    @CollectionTable(name = "dropout_by_admission_type", joinColumns = @JoinColumn(name = "dropout_by_admission_type_data_id"))
    @MapKeyColumn(name = "admission_type")
    @Column(name = "count")
    private Map<String, Double> dropoutByAdmissionType;
    
}
