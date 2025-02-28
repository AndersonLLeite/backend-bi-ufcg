package com.ufcg.bi.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternshipProviderDTO {
   
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("razao_social")
    private String razaoSocial;
    @JsonProperty("municipio")
    private String municipio;
    @JsonProperty("orgao_administracao_publica")
    private Boolean orgaoAdministracaoPublica;

}
