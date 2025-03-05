package com.ufcg.bi.services;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.subject.Subject;
import com.ufcg.bi.services.course.CourseService;
import com.ufcg.bi.services.docentes.TeacherService;
import com.ufcg.bi.services.internship.InternshipService;
import com.ufcg.bi.services.subjectService.SubjectService;

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

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private InternshipService internshipService;

    
    //@Scheduled(cron = "0 0 0 * * *") // Executar uma vez por dia
    @Transactional
public void synchronizeData() {
    LOGGER.info("Iniciando sincronização de dados...");
    try {
        synchronizeCourses();
        synchronizeTeachers();
        synchronizeSubjects();
        synchronizeInternships();
        
    } catch (Exception e) {
        LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
    }
            LOGGER.info("Sincronização concluída.");
        }
        

private void synchronizeCourses() {
    try {
        courseService.fetchCourses();
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar cursos: {}", e.getMessage());
    }

}

private void synchronizeTeachers() {
    LOGGER.info("entrou no processo de professores");
    try {
        teacherService.fetchTeachers();
        
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar e salvar professores: {}", e.getMessage());
    }
}

private void synchronizeSubjects() {
    try {
         subjectService.fetchSubjects();;
        
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar e salvar disciplinas: {}", e.getMessage());
    }
}

private void synchronizeInternships() {
    try {
        internshipService.fetchInternships();
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar e salvar estágios: {}", e.getMessage());
    }

}
}