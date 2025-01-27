package com.ufcg.bi.services;

import com.ufcg.bi.models.*;

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
