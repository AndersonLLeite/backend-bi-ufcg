package com.ufcg.bi.services;

import com.ufcg.bi.models.*;
import com.ufcg.bi.models.docentes.Teacher;
import com.ufcg.bi.services.campus.DropoutAndEntryCountService;
import com.ufcg.bi.services.campus.StudentCenterDistributionService;
import com.ufcg.bi.services.campus.StudentCountService;
import com.ufcg.bi.services.campus.StudentStatusDistributionService;
import com.ufcg.bi.services.discentes.AgeAtEnrollmentService;
import com.ufcg.bi.services.discentes.ColorDataService;
import com.ufcg.bi.services.discentes.DisabilitiesDataService;
import com.ufcg.bi.services.discentes.GenderDataService;
import com.ufcg.bi.services.discentes.InactivityDataService;
import com.ufcg.bi.services.discentes.PolicyDataService;
import com.ufcg.bi.services.discentes.SecondarySchoolTypeService;
import com.ufcg.bi.services.docentes.TeacherService;
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
    private CourseService courseService;
    

    @Autowired
    private TeacherService teacherService;

    
    //@Scheduled(cron = "0 0 0 * * *") // Executar uma vez por dia
    @Transactional
public void synchronizeData() {
    LOGGER.info("Iniciando sincronização de dados...");
    try {
       // synchronizeCourses();
        synchronizeTeachers();
    } catch (Exception e) {
        LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
    }
    LOGGER.info("Sincronização concluída.");
}

private void synchronizeCourses() {
    try {
        List<Course> courses = courseService.fetchCourses();
        LOGGER.info("Cursos obtidos: {}", courses.size());

        for (Course course : courses) {
            try {
                courseService.processCourse(course);     
            } catch (Exception e) {
                LOGGER.error("Erro ao processar o curso '{}': {}", course.getDescricao(), e.getMessage());
            }
        }
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar cursos: {}", e.getMessage());
    }
}



private void synchronizeTeachers() {
    try {
        List<Teacher> teachers = teacherService.fetchTeachers();
        LOGGER.info("Professores obtidos: {}", teachers.size());
        teacherService.saveTeachers(teachers);
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar e salvar professores: {}", e.getMessage());
    }
}



}
