package com.ufcg.bi.models;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataTeste {

    private int codigoDoCurso;

    
    private String descricao;

    private String status;

    private int codigoDoSetor;

    private String nomeDoSetor;

    private List<Student> students;

    private String grauDoCurso;

  
    private Integer campus;

   
    private String nomeDoCampus;

    private String turno;

    private String periodoDeInicio;


    private String dataDeFuncionamento;

    private Integer codigoInep;

    private String modalidadeAcademica;


    private Integer curriculoAtual;


    private Integer areaDeRetencao;

    private Integer cicloEnade;
     private Map<String, Map<String, Double>> genderDistribution;
    private Map<String, Map<String, Double>> ageDistribution;
    private Map<String, Map<String, Double>> affirmativePolicyDistribution;
    private Map<String, Map<String, Double>> statusDistribution;
    private Map<String, Map<String, Double>> inactivityReasonsDistribution;
    private Map<String, Map<String, Double>> admissionTypeDistribution;
    private Map<String, Map<String, Double>> secondarySchoolTypeDistribution;
    private Map<String, Map<String, Double>> originDistribution;
    private Map<String, Map<String, Double>> colorDistribution;
    private Map<String, Map<String, Double>> disabilitiesDistribution;
    private Map<String, Map<String, Double>> inactivityPerPeriodoDeEvasaoDistribution;
    private Map<String, Map<String, Double>> ageAtEnrollmentDistribution;
    private Map<String, Map<String, Double>> creditCompletedVsFailedDistribution;
    private Map<String, Map<String, Double>> evasionStatisticsByEvasionPeriod;
    private Map<String, Map<String, Double>> graduationStatisticsByEvasionPeriod;
    private Map<String, Map<String, Double>> evasionByColor;
    private Map<String, Map<String, Double>> evasionByAge;
    private Map<String, Map<String, Double>> evasionByGender;
    private Map<String, Map<String, Double>> evasionByAdmissionType;
    private Map<String, Map<String, Double>> evasionBySecondarySchoolType;
    private Map<String, Map<String, Double>> evasionByDisabilities;
}
