package com.ufcg.bi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @JsonProperty("codigo_do_curso")
    @Column(name = "codigo_do_curso")
    private Integer codigoDoCurso;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("idade")
    private String idade;

    @JsonProperty("situacao")
    private String situacao;

    @JsonProperty("motivo_de_evasao")
    @Column(name = "motivo_de_evasao")
    private String motivoDeEvasao;

    @JsonProperty("periodo_de_evasao")
    @Column(name = "periodo_de_evasao")
    private String periodoDeEvasao;

    @JsonProperty("forma_de_ingresso")
    @Column(name = "forma_de_ingresso")
    private String formaDeIngresso;

    @JsonProperty("periodo_de_ingresso")
    @Column(name = "periodo_de_ingresso")
    private String periodoDeIngresso;

    @JsonProperty("naturalidade")
    private String naturalidade;

    @JsonProperty("cor")
    private String cor;

    @ElementCollection
    @CollectionTable(name = "student_deficiencias", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "deficiencia")
    @JsonProperty("deficiencias")
    private List<String> deficiencias;

    @JsonProperty("tipo_de_ensino_medio")
    @Column(name = "tipo_de_ensino_medio")
    private String tipoDeEnsinoMedio;

    @JsonProperty("politica_afirmativa")
    @Column(name = "politica_afirmativa")
    private String politicaAfirmativa;

    @Id
    @JsonProperty("matricula_do_estudante")
    @Column(name = "matricula_do_estudante", unique = true, nullable = false)
    private String matriculaDoEstudante;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("nome_do_curso")
    @Column(name = "nome_do_curso")
    private String nomeDoCurso;

    @JsonProperty("turno_do_curso")
    @Column(name = "turno_do_curso")
    private String turnoDoCurso;

    @JsonProperty("codigo_do_curriculo")
    @Column(name = "codigo_do_curriculo")
    private Integer codigoDoCurriculo;

    @JsonProperty("campus")
    private Integer campus;

    @JsonProperty("nome_do_campus")
    @Column(name = "nome_do_campus")
    private String nomeDoCampus;

    @JsonProperty("codigo_do_setor")
    @Column(name = "codigo_do_setor")
    private Integer codigoDoSetor;

    @JsonProperty("nome_do_setor")
    @Column(name = "nome_do_setor")
    private String nomeDoSetor;

    @JsonProperty("estado_civil")
    @Column(name = "estado_civil")
    private String estadoCivil;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("data_de_nascimento")
    @Column(name = "data_de_nascimento")
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
    @Column(name = "local_de_nascimento")
    private String localDeNascimento;

    @JsonProperty("ano_de_conclusao_ensino_medio")
    @Column(name = "ano_de_conclusao_ensino_medio")
    private Integer anoDeConclusaoEnsinoMedio;

    @JsonProperty("creditos_do_cra")
    @Column(name = "creditos_do_cra")
    private Integer creditosDoCra;

    @JsonProperty("notas_acumuladas")
    @Column(name = "notas_acumuladas")
    private Double notasAcumuladas;

    @JsonProperty("periodos_completados")
    @Column(name = "periodos_completados")
    private Integer periodosCompletados;

    @JsonProperty("creditos_tentados")
    @Column(name = "creditos_tentados")
    private Integer creditosTentados;

    @JsonProperty("creditos_completados")
    @Column(name = "creditos_completados")
    private Integer creditosCompletados;

    @JsonProperty("creditos_isentos")
    @Column(name = "creditos_isentos")
    private Integer creditosIsentos;

    @JsonProperty("creditos_falhados")
    @Column(name = "creditos_falhados")
    private Integer creditosFalhados;

    @JsonProperty("creditos_suspensos")
    @Column(name = "creditos_suspensos")
    private Integer creditosSuspensos;

    @JsonProperty("creditos_em_andamento")
    @Column(name = "creditos_em_andamento")
    private Integer creditosEmAndamento;

    @JsonProperty("velocidade_media")
    @Column(name = "velocidade_media")
    private Double velocidadeMedia;

    @JsonProperty("taxa_de_sucesso")
    @Column(name = "taxa_de_sucesso")
    private Double taxaDeSucesso;

    @JsonProperty("prac_atualizado")
    @Column(name = "prac_atualizado")
    private String pracAtualizado;

    @JsonProperty("prac_atualizado_em")
    @Column(name = "prac_atualizado_em")
    private String pracAtualizadoEm;

    @JsonProperty("prac_cor")
    @Column(name = "prac_cor")
    private String pracCor;

    @JsonProperty("prac_quilombola")
    @Column(name = "prac_quilombola")
    private String pracQuilombola;

    @JsonProperty("prac_indigena_aldeado")
    @Column(name = "prac_indigena_aldeado")
    private String pracIndigenaAldeado;

    @JsonProperty("prac_renda_per_capita_ate")
    @Column(name = "prac_renda_per_capita_ate")
    private Double pracRendaPerCapitaAte;

    @JsonProperty("prac_deficiente")
    @Column(name = "prac_deficiente")
    private String pracDeficiente;

    @ElementCollection
    @CollectionTable(name = "student_prac_deficiencias", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "prac_deficiencia")
    @JsonProperty("prac_deficiencias")
    private List<String> pracDeficiencias;

    @JsonProperty("prac_deslocou_mudou")
    @Column(name = "prac_deslocou_mudou")
    private String pracDeslocouMudou;

    @JsonProperty("ufpb")
    private Integer ufpb;
}

