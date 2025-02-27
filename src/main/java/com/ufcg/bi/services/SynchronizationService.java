package com.ufcg.bi.services;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.services.course.CourseService;
import com.ufcg.bi.services.docentes.TeacherService;
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
        List<Course> courses = courseService.fetchCourses();
        LOGGER.info("Cursos obtidos: {}", courses.size());
        synchronizeCourses(courses);
        synchronizeTeachers(getCampusCodeList(courses));
    } catch (Exception e) {
        LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
    }
            LOGGER.info("Sincronização concluída.");
        }
        
private List<Integer> getCampusCodeList(List<Course> courses) {
    Set<Integer> campusCodeList = new HashSet<>();
    for (Course course : courses) {
        campusCodeList.add(course.getCampus());
    }
    return new ArrayList<>(campusCodeList);
}

private void synchronizeCourses(List<Course> courses) {
    try {
        Set<Integer> campusCodeList = new HashSet<>();
        for (int i = 0; i < courses.size(); i++) {
            try {
                courseService.processCourse(courses.get(i)); 
                campusCodeList.add(courses.get(i).getCampus());    
            } catch (Exception e) {
                LOGGER.error("Erro ao processar o curso '{}': {}", courses.get(i).getDescricao(), e.getMessage());
            }
        }
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar cursos: {}", e.getMessage());
    }

}

private void synchronizeTeachers(List<Integer> campusCodeList) {
    try {
        for (Integer campusCode : campusCodeList) {
            teacherService.fetchTeachersByCampusCode(campusCode);
        }
    } catch (Exception e) {
        LOGGER.error("Erro ao buscar e salvar professores: {}", e.getMessage());
    }
}



}
