package com.ufcg.bi.DTO.internshipDTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternshipDTO {
   
    private int id;
    private int idConcedente;
    private String ufConcedente;
    private String departamento;
    private boolean obrigatorio;
    private String status;
    private int bolsaMensal;
    private int auxilioTransporteDiario;

}
