package com.ufcg.bi.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "terms")
public class Terms {

    @Id
    private Long id = 1L; // ID fixo para garantir unicidade
    @ElementCollection
    private List<String> terms;

    public Terms(List<String> terms) {
        this.terms = terms;
    }
}
