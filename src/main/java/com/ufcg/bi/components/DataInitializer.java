package com.ufcg.bi.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ufcg.bi.models.Data2;
import com.ufcg.bi.services.DataService2;

import java.util.List;
import java.util.Map;



    @Component
    public class DataInitializer implements CommandLineRunner {
    
        private final DataService2 dataService;
    
        public DataInitializer(DataService2 dataService) {
            this.dataService = dataService;
        }
    
        @Override
        public void run(String... args) {
            // Dados para o primeiro objeto
            Map<String, Double> genderDist1 = Map.of( "FEMININO", 45.0);
            Map<String, Double> ageDist1 = Map.of("16-18", 2.0, "25-27", 11.0, "22-24", 45.0, "19-21", 122.0, "31+", 27.0, "28-30", 8.0);
            Map<String, Double> affPolicyDist1 = Map.of("Bon. estadual", 61.0, "L1", 20.0, "L2", 31.0, "L5", 13.0, "L6", 33.0, "-", 57.0, "L10", 5.0);
            Map<String, Double> inactivityReason1 = Map.of("TRANSFERENCIA", 5.0, "CANCELAMENTO", 3.0);
            Map<String, Double> admissionType1 = Map.of("VESTIBULAR", 70.0, "ENEM", 30.0);
            Map<String, Double> schoolType1 = Map.of("PUBLICA", 80.0, "PARTICULAR", 20.0);
            Map<String, Double> colorDist1 = Map.of("BRANCA", 50.0, "PRETA", 30.0, "PARDA", 20.0);
            Map<String, Double> disabilitiesDist1 = Map.of("AUDITIVA", 5.0, "VISUAL", 3.0);
            Map<String, Double> ageEnrollment1 = Map.of("18-20", 60.0, "21-23", 40.0);
            Map<String, Double> evasionStats1 = Map.of("2023.1", 10.0, "2022.2", 15.0);
            Map<String, Double> graduationStats1 = Map.of("2023.1", 5.0, "2022.2", 12.0);
            Map<String, Double> evasionByAge1 = Map.of("18-20", 4.0, "21-23", 6.0);
            Map<String, Double> evasionByGender1 = Map.of("MASCULINO", 7.0, "FEMININO", 3.0);
            Map<String, Double> evasionByAdmission1 = Map.of("ENEM", 2.0, "VESTIBULAR", 8.0);
            Map<String, Double> evasionBySchoolType1 = Map.of("PUBLICA", 1111119.0, "PARTICULAR", 1.0);
            Map<String, Double> evasionByDisabilities1 = Map.of("AUDITIVA", 1.0);
    
            Data2 data1 = new Data2(
                "1", 101, "Computer Science", "Active", 1, "IT Department", 1, "Main Campus", "2023.1",
                genderDist1, ageDist1, affPolicyDist1, inactivityReason1, admissionType1, schoolType1,
                colorDist1, disabilitiesDist1, ageEnrollment1, evasionStats1, graduationStats1,
                evasionByAge1, evasionByGender1, evasionByAdmission1, evasionBySchoolType1, evasionByDisabilities1
            );
    
            // Dados para o segundo objeto
            Map<String, Double> genderDist2 = Map.of( "FEMININO", 30.0);
            Map<String, Double> ageDist2 = Map.of("16-18", 5.0, "25-27", 15.0, "22-24", 50.0, "19-21", 100.0, "31+", 30.0, "28-30", 10.0);
            Map<String, Double> affPolicyDist2 = Map.of("Bon. estadual", 70.0, "L1", 25.0, "L2", 20.0, "L5", 10.0, "L6", 35.0, "-", 40.0);
            Map<String, Double> inactivityReason2 = Map.of("TRANSFERENCIA", 8.0, "CANCELAMENTO", 2.0);
            Map<String, Double> admissionType2 = Map.of("VESTIBULAR", 60.0, "ENEM", 40.0);
            Map<String, Double> schoolType2 = Map.of("PUBLICA", 75.0, "PARTICULAR", 25.0);
            Map<String, Double> colorDist2 = Map.of("BRANCA", 55.0, "PRETA", 25.0, "PARDA", 20.0);
            Map<String, Double> disabilitiesDist2 = Map.of("AUDITIVA", 4.0, "VISUAL", 2.0);
            Map<String, Double> ageEnrollment2 = Map.of("18-20", 50.0, "21-23", 50.0);
            Map<String, Double> evasionStats2 = Map.of("2023.1", 12.0, "2022.2", 10.0);
            Map<String, Double> graduationStats2 = Map.of("2023.1", 8.0, "2022.2", 15.0);
            Map<String, Double> evasionByAge2 = Map.of("18-20", 6.0, "21-23", 6.0);
            Map<String, Double> evasionByGender2 = Map.of("MASCULINO", 9.0, "FEMININO", 3.0);
            Map<String, Double> evasionByAdmission2 = Map.of("ENEM", 3.0, "VESTIBULAR", 9.0);
            Map<String, Double> evasionBySchoolType2 = Map.of("PUBLICA", 8.0, "PARTICULAR", 2.0);
            Map<String, Double> evasionByDisabilities2 = Map.of("VISUAL", 2.0);
    
            Data2 data2 = new Data2(
                "2", 102, "Mathematics", "Active", 2, "Math Department", 1, "Main Campus", "2023.1",
                genderDist2, ageDist2, affPolicyDist2, inactivityReason2, admissionType2, schoolType2,
                colorDist2, disabilitiesDist2, ageEnrollment2, evasionStats2, graduationStats2,
                evasionByAge2, evasionByGender2, evasionByAdmission2, evasionBySchoolType2, evasionByDisabilities2
            );
    
            // Salva os objetos
            //dataService.saveAll(List.of(data1, data2));
        }
    }
    

