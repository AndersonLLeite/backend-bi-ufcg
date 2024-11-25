package com.ufcg.bi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filter_data")
public class FilterData {

    @Id
    private Long id = 1L; // ID fixo para garantir unicidade

    private String startTerm;
    private String endTerm;

    // Relacionamento ManyToMany com as entidades Campus, Centro e Curso
    @ManyToMany
    @JoinTable(
            name = "filter_data_centros",
            joinColumns = @JoinColumn(name = "filter_data_id"),
            inverseJoinColumns = @JoinColumn(name = "centro_id")
    )
    private List<Centro> centros;

    @ManyToMany
    @JoinTable(
            name = "filter_data_campus",
            joinColumns = @JoinColumn(name = "filter_data_id"),
            inverseJoinColumns = @JoinColumn(name = "campus_id")
    )
    private List<Campus> campus;

    @ManyToMany
    @JoinTable(
            name = "filter_data_cursos",
            joinColumns = @JoinColumn(name = "filter_data_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos;

    @ElementCollection
    private List<String> terms;

    public List<String> termsInRange() {
        if (terms == null || terms.isEmpty()) {
            return new ArrayList<>();
        }

        if (startTerm == null && endTerm == null) {
            return new ArrayList<>(terms);
        }

        int startIndex = 0;
        int endIndex = terms.size() - 1;

        if (startTerm != null) {
            startIndex = terms.indexOf(startTerm);
            if (startIndex == -1) {
                startIndex = 0;
            }
        }

        if (endTerm != null) {
            endIndex = terms.indexOf(endTerm);
            if (endIndex == -1) {
                endIndex = terms.size() - 1;
            }
        }

        return terms.subList(startIndex, endIndex + 1);
    }
}
