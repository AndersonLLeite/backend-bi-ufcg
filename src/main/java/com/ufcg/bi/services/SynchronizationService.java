package com.ufcg.bi.services;

import com.ufcg.bi.models.*;
import com.ufcg.bi.services.campus.DropoutAndEntryCountService;
import com.ufcg.bi.services.campus.StudentCenterDistributionService;
import com.ufcg.bi.services.campus.StudentCountService;
import com.ufcg.bi.services.campus.StudentStatusDistributionService;
import com.ufcg.bi.services.discentes.AgeAtEnrollmentService;
import com.ufcg.bi.services.discentes.AgeDataService;
import com.ufcg.bi.services.discentes.ColorDataService;
import com.ufcg.bi.services.discentes.CourseService;
import com.ufcg.bi.services.discentes.DisabilitiesDataService;
import com.ufcg.bi.services.discentes.GenderDataService;
import com.ufcg.bi.services.discentes.InactivityDataService;
import com.ufcg.bi.services.discentes.PolicyDataService;
import com.ufcg.bi.services.evasao.DropoutByAdmissionTypeDataService;
import com.ufcg.bi.services.evasao.DropoutByAgeDataService;
import com.ufcg.bi.services.evasao.DropoutByColorDataService;
import com.ufcg.bi.services.evasao.DropoutByDisabilityDataService;
import com.ufcg.bi.services.evasao.DropoutByGenderDataService;
import com.ufcg.bi.services.evasao.DropoutBySecondarySchoolTypeDataService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class SynchronizationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizationService.class);

    @Autowired
    private FilterDataService filterDataService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AgeDataService ageDataService;

    @Autowired
    private GenderDataService genderDataService;

    @Autowired
    private PolicyDataService policyDataService;

    @Autowired
    private InactivityDataService inactivityDataService;

    @Autowired
    private AgeAtEnrollmentService ageAtEnrollmentService;

    @Autowired
    private ColorDataService colorDataService;

    @Autowired
    private DisabilitiesDataService disabilitiesDataService;

    @Autowired
    private DropoutByColorDataService dropoutByColorDataService;

    @Autowired
    private DropoutByGenderDataService dropoutByGenderDataService;

    @Autowired
    private DropoutByAgeDataService dropoutByAgeDataService;

    @Autowired
    private DropoutByDisabilityDataService dropoutByDisabilityDataService;

    @Autowired
    private DropoutByAdmissionTypeDataService dropoutByAdmissionTypeDataService;

    @Autowired
    private DropoutBySecondarySchoolTypeDataService dropoutBySecondarySchoolTypeDataService;

    @Autowired
    private StudentCenterDistributionService studentCenterDistributionService;

    @Autowired
    private StudentStatusDistributionService studentStatusDistributionService;

    @Autowired
    private StudentCountService studentCountService;

    @Autowired 
    private DropoutAndEntryCountService dropoutAndEntryCountService;

    
    //@Scheduled(cron = "0 0 0 * * *") // Executar uma vez por dia
    @Transactional
    public void synchronizeData() {
        LOGGER.info("Iniciando sincronização de dados...");

        try {
            List<Course> courses = courseService.fetchCourses();
            LOGGER.info("Cursos obtidos: {}", courses.size());

            // int limit = Math.min(10, courses.size()); // Limita a 10 cursos para teste
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                try {
                    Course courseProcessed = courseService.processCourse(course);
                    List<String> terms = courseProcessed.getPeriodos();
                    for (String term : terms) {
                        filterDataService.createFilterData(course, term);
                        ageDataService.createAgeData(courseProcessed, term);
                        genderDataService.createGenderData(courseProcessed, term);
                        policyDataService.createPolicyData(courseProcessed, term);
                        inactivityDataService.createInactivityData(courseProcessed, term);
                        ageAtEnrollmentService.createAgeAtEnrollment(courseProcessed, term);
                        colorDataService.createColorData(courseProcessed, term);
                        disabilitiesDataService.createDisabilitiesData(courseProcessed, term);
                        dropoutByColorDataService.createDropoutByColorData(courseProcessed, term);
                        dropoutByGenderDataService.createDropoutByGenderData(courseProcessed, term);
                        dropoutByAgeDataService.createDropoutByAgeData(courseProcessed, term);
                        dropoutByDisabilityDataService.createDropoutByDisabilityData(courseProcessed, term);
                        dropoutByAdmissionTypeDataService.createDropoutByAdmissionTypeData(courseProcessed, term);
                        dropoutBySecondarySchoolTypeDataService.createDropoutBySecondarySchoolTypeData(courseProcessed, term);
                        studentCenterDistributionService.createStudentCenterDistribution(courseProcessed, term);
                        studentStatusDistributionService.createStudentStatusDistribution(courseProcessed, term);
                        studentCountService.createStudentCount(courseProcessed, term);
                        dropoutAndEntryCountService.createDropoutAndEntryCount(courseProcessed, term);


                    }
                   
                } catch (Exception e) {
                    LOGGER.error("Erro ao processar o curso '{}': {}", course.getDescricao(), e.getMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
        }

        LOGGER.info("Sincronização concluída.");
    }



}
