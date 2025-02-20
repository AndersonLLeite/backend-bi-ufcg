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
public class DropoutByColorData {
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
    @CollectionTable(name = "dropout_by_color", joinColumns = @JoinColumn(name = "dropout_by_color_data_id"))
    @MapKeyColumn(name = "color")
    @Column(name = "count")
    private Map<String, Double> dropoutByColor;
}
