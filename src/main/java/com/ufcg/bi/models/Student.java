package com.ufcg.bi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    
    private Course course;
    @JsonProperty("codigo_do_curso")
    private Integer codigoDoCurso;
    @JsonProperty("genero")
    private String genero;
    @JsonProperty("idade")
    private String idade;
    @JsonProperty("situacao")
    private String situacao;
    @JsonProperty("motivo_de_evasao")
    private String motivoDeEvasao;
    @JsonProperty("periodo_de_evasao")
    private String periodoDeEvasao;
    @JsonProperty("forma_de_ingresso")
    private String formaDeIngresso;
    @JsonProperty("periodo_de_ingresso")
    private String periodoDeIngresso;
    @JsonProperty("naturalidade")
    private String naturalidade;
    @JsonProperty("cor")
    private String cor;
    @JsonProperty("deficiencias")
    private List<String> deficiencias;
    @JsonProperty("tipo_de_ensino_medio")
    private String tipoDeEnsinoMedio;
    @JsonProperty("politica_afirmativa")
    private String politicaAfirmativa;
    @JsonProperty("matricula_do_estudante")
    private String matriculaDoEstudante;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("nome_do_curso")
    private String nomeDoCurso;
    @JsonProperty("turno_do_curso")
    private String turnoDoCurso;
    @JsonProperty("codigo_do_curriculo")
    private Integer codigoDoCurriculo;
    @JsonProperty("campus")
    private Integer campus;
    @JsonProperty("nome_do_campus")
    private String nomeDoCampus;
    @JsonProperty("codigo_do_setor")
    private Integer codigoDoSetor;
    @JsonProperty("nome_do_setor")
    private String nomeDoSetor;
    @JsonProperty("estado_civil")
    private String estadoCivil;
    @JsonProperty("endereco")
    private String endereco;
    @JsonProperty("data_de_nascimento")
    private String dataDeNascimento;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("nacionalidade")
    private String nacionalidade;
    @JsonProperty("local_de_nascimento")
    private String localDeNascimento;
    @JsonProperty("ano_de_conclusao_ensino_medio")
    private Integer anoDeConclusaoEnsinoMedio;
    @JsonProperty("creditos_do_cra")
    private Integer creditosDoCra;
    @JsonProperty("notas_acumuladas")
    private Double notasAcumuladas;
    @JsonProperty("periodos_completados")
    private Integer periodosCompletados;
    @JsonProperty("creditos_tentados")
    private Integer creditosTentados;
    @JsonProperty("creditos_completados")
    private Integer creditosCompletados;
    @JsonProperty("creditos_isentos")
    private Integer creditosIsentos;
    @JsonProperty("creditos_falhados")
    private Integer creditosFalhados;
    @JsonProperty("creditos_suspensos")
    private Integer creditosSuspensos;
    @JsonProperty("creditos_em_andamento")
    private Integer creditosEmAndamento;
    @JsonProperty("velocidade_media")
    private Double velocidadeMedia;
    @JsonProperty("taxa_de_sucesso")
    private Double taxaDeSucesso;
    @JsonProperty("prac_atualizado")
    private String pracAtualizado;
    @JsonProperty("prac_atualizado_em")
    private String pracAtualizadoEm;
    @JsonProperty("prac_cor")
    private String pracCor;
    @JsonProperty("prac_quilombola")
    private String pracQuilombola;
    @JsonProperty("prac_indigena_aldeado")
    private String pracIndigenaAldeado;
    @JsonProperty("prac_renda_per_capita_ate")
    private Double pracRendaPerCapitaAte;
    @JsonProperty("prac_deficiente")
    private String pracDeficiente;
    @JsonProperty("prac_deficiencias")
    private List<String> pracDeficiencias;
    @JsonProperty("prac_deslocou_mudou")
    private String pracDeslocouMudou;
    @JsonProperty("ufpb")
    private Integer ufpb;
}

