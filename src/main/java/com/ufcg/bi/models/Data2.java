package com.ufcg.bi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data2 {

    // @Id
    private String id;

    private int codigoDoCurso;
    private String curso;
    private String status;
    private int codigoDoSetor;
    private String setor;
    private Integer codigoDoCampus;
    private String campus;
    private String periodo;

    // @ElementCollection
    // @CollectionTable(name = "gender_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "gender")
    // @Column(name = "count")
    private Map<String, Double> sexo;

    // @ElementCollection
    // @CollectionTable(name = "age_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "age_range")
    // @Column(name = "count")
    private Map<String, Double> idade;

    // @ElementCollection
    // @CollectionTable(name = "affirmative_policy_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "policy")
    // @Column(name = "count")
    private Map<String, Double> politicaAfirmativa;

    // @ElementCollection
    // @CollectionTable(name = "inactivity_reason_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "reason")
    // @Column(name = "count")
    private Map<String, Double> inactivityReasonDistribution;

    // @ElementCollection
    // @CollectionTable(name = "admission_type_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "admission_type")
    // @Column(name = "count")
    private Map<String, Double> admissionTypeDistribution;

    // @ElementCollection
    // @CollectionTable(name = "secondary_school_type_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "school_type")
    // @Column(name = "count")
    private Map<String, Double> secondarySchoolTypeDistribution;

    // @ElementCollection
    // @CollectionTable(name = "color_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "color")
    // @Column(name = "count")
    private Map<String, Double> colorDistribution;

    // @ElementCollection
    // @CollectionTable(name = "disabilities_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "disability")
    // @Column(name = "count")
    private Map<String, Double> disabilitiesDistribution;

    // @ElementCollection
    // @CollectionTable(name = "age_at_enrollment_distribution", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "age_at_enrollment")
    // @Column(name = "count")
    private Map<String, Double> ageAtEnrollmentDistribution;

    // @ElementCollection
    // @CollectionTable(name = "evasion_statistics_by_period", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "evasion_period")
    // @Column(name = "count")
    private Map<String, Double> evasionStatisticsByEvasionPeriod;

    // @ElementCollection
    // @CollectionTable(name = "graduation_statistics_by_period", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "graduation_period")
    // @Column(name = "count")
    private Map<String, Double> graduationStatisticsByEvasionPeriod;

    // @ElementCollection
    // @CollectionTable(name = "evasion_by_age", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "age")
    // @Column(name = "count")
    private Map<String, Double> evasionByAge;

    // @ElementCollection
    // @CollectionTable(name = "evasion_by_gender", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "gender")
    // @Column(name = "count")
    private Map<String, Double> evasionByGender;

    // @ElementCollection
    // @CollectionTable(name = "evasion_by_admission_type", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "admission_type")
    // @Column(name = "count")
    private Map<String, Double> evasionByAdmissionType;

    // @ElementCollection
    // @CollectionTable(name = "evasion_by_secondary_school_type", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "school_type")
    // @Column(name = "count")
    private Map<String, Double> evasionBySecondarySchoolType;

    // @ElementCollection
    // @CollectionTable(name = "evasion_by_disability", joinColumns = @JoinColumn(name = "data_id"))
    // @MapKeyColumn(name = "disability")
    // @Column(name = "count")
    private Map<String, Double> evasionByDisabilitie;
}
