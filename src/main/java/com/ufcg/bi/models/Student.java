package com.ufcg.bi.models;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "codigo_do_curso")
    private Integer codigoDoCurso;

    private String genero;
    private String idade;
    private String situacao;

    @Column(name = "motivo_de_evasao")
    private String motivoDeEvasao;

    @Column(name = "periodo_de_evasao")
    private String periodoDeEvasao;

    @Column(name = "forma_de_ingresso")
    private String formaDeIngresso;

    @Column(name = "periodo_de_ingresso")
    private String periodoDeIngresso;

    private String naturalidade;
    private String cor;

    @ElementCollection
    @CollectionTable(name = "student_deficiencias", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "deficiencia")
    private List<String> deficiencias;

    @Column(name = "tipo_de_ensino_medio")
    private String tipoDeEnsinoMedio;

    @Column(name = "politica_afirmativa")
    private String politicaAfirmativa;

    @Column(name = "matricula_do_estudante", unique = true, nullable = false)
    private String matriculaDoEstudante;

    private String nome;

    @Column(name = "nome_do_curso")
    private String nomeDoCurso;

    @Column(name = "turno_do_curso")
    private String turnoDoCurso;

    @Column(name = "codigo_do_curriculo")
    private Integer codigoDoCurriculo;

    private Integer campus;

    @Column(name = "nome_do_campus")
    private String nomeDoCampus;

    @Column(name = "codigo_do_setor")
    private Integer codigoDoSetor;

    @Column(name = "nome_do_setor")
    private String nomeDoSetor;

    @Column(name = "estado_civil")
    private String estadoCivil;

    private String endereco;

    @Column(name = "data_de_nascimento")
    private String dataDeNascimento;

    private String cpf;
    private String cep;
    private String telefone;
    private String email;
    private String nacionalidade;

    @Column(name = "local_de_nascimento")
    private String localDeNascimento;

    @Column(name = "ano_de_conclusao_ensino_medio")
    private Integer anoDeConclusaoEnsinoMedio;

    @Column(name = "creditos_do_cra")
    private Integer creditosDoCra;

    @Column(name = "notas_acumuladas")
    private Double notasAcumuladas;

    @Column(name = "periodos_completados")
    private Integer periodosCompletados;

    @Column(name = "creditos_tentados")
    private Integer creditosTentados;

    @Column(name = "creditos_completados")
    private Integer creditosCompletados;

    @Column(name = "creditos_isentos")
    private Integer creditosIsentos;

    @Column(name = "creditos_falhados")
    private Integer creditosFalhados;

    @Column(name = "creditos_suspensos")
    private Integer creditosSuspensos;

    @Column(name = "creditos_em_andamento")
    private Integer creditosEmAndamento;

    @Column(name = "velocidade_media")
    private Double velocidadeMedia;

    @Column(name = "taxa_de_sucesso")
    private Double taxaDeSucesso;

    @Column(name = "prac_atualizado")
    private String pracAtualizado;

    @Column(name = "prac_atualizado_em")
    private String pracAtualizadoEm;

    @Column(name = "prac_cor")
    private String pracCor;

    @Column(name = "prac_quilombola")
    private String pracQuilombola;

    @Column(name = "prac_indigena_aldeado")
    private String pracIndigenaAldeado;

    @Column(name = "prac_renda_per_capita_ate")
    private Double pracRendaPerCapitaAte;

    @Column(name = "prac_deficiente")
    private String pracDeficiente;

    @ElementCollection
    @CollectionTable(name = "student_prac_deficiencias", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "prac_deficiencia")
    private List<String> pracDeficiencias;

    @Column(name = "prac_deslocou_mudou")
    private String pracDeslocouMudou;

    private Integer ufpb;
}
