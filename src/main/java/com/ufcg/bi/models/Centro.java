package com.ufcg.bi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "centro")
public class Centro {

    @Id
    private Long id;

    private String nome;

}
