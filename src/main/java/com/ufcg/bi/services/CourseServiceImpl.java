package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCourses(List<Integer> courseCodes) {
        // Chama o repositório para recuperar os cursos com base nos filtros fornecidos
        return courseRepository.findByCodigoDoCursoIn(courseCodes);
    }
}
