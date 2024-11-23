//package com.ufcg.bi.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Map;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "data_entity")
//public class DataEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ElementCollection
//    @CollectionTable(name = "gender_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> genderDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "age_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> ageDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "affirmative_policy_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> affirmativePolicyDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "status_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> statusDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "inactivity_reasons_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> inactivityReasonsDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "admission_type_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> admissionTypeDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "secondary_school_type_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> secondarySchoolTypeDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "origin_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> originDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "color_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> colorDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "disabilities_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> disabilitiesDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "inactivity_per_periodo_de_evasao_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> inactivityPerPeriodoDeEvasaoDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "age_at_enrollment_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> ageAtEnrollmentDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "credit_completed_vs_failed_distribution", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> creditCompletedVsFailedDistribution;
//
//    @ElementCollection
//    @CollectionTable(name = "evasion_statistics_by_evasion_period", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> evasionStatisticsByEvasionPeriod;
//
//    @ElementCollection
//    @CollectionTable(name = "graduation_statistics_by_evasion_period", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> graduationStatisticsByEvasionPeriod;
//
//    @ElementCollection
//    @CollectionTable(name = "evasion_by_color", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> evasionByColor;
//
//    @ElementCollection
//    @CollectionTable(name = "evasion_by_age", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> evasionByAge;
//
//    @ElementCollection
//    @CollectionTable(name = "evasion_by_gender", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> evasionByGender;
//
//    @ElementCollection
//    @CollectionTable(name = "evasion_by_admission_type", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> evasionByAdmissionType;
//
//    @ElementCollection
//    @CollectionTable(name = "evasion_by_secondary_school_type", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> evasionBySecondarySchoolType;
//
//    @ElementCollection
//    @CollectionTable(name = "evasion_by_disabilities", joinColumns = @JoinColumn(name = "data_entity_id"))
//    @MapKeyColumn(name = "key")
//    @Column(name = "value")
//    private Map<String, Map<String, Double>> evasionByDisabilities;
//}
