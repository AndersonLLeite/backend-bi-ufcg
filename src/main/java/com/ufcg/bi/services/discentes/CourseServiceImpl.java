package com.ufcg.bi.services.discentes;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.repositories.discentes.CourseRepository;
import com.ufcg.bi.services.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final WebClient webClient;

    @Autowired
    private StudentService studentService;

    @Autowired
    public CourseServiceImpl(
            CourseRepository courseRepository,
            @Value("${app.service.base-url}") String baseUrl) {
        this.courseRepository = courseRepository;
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }


    @Override
    public List<Course> fetchCourses() {
        Mono<List<Course>> response = webClient.get()
                .uri("/cursos")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Course>>() {});
        return response.block(); 
    }

    @Override
    public Course processCourse(Course course) {
        List<Student> students = studentService.fetchStudents(course.getCodigoDoCurso());
        LOGGER.info("Estudantes obtidos para o curso {}: {}", course.getCodigoDoCurso(), students.size());
    
        course.setStudents(students);
        Set<String> termsSet = new HashSet<>();
    
        for (Student student : students) {
            student.setCourse(course);
            termsSet.add(student.getPeriodoDeIngresso());
        }
    
        course.setPeriodos(new ArrayList<>(termsSet));
        LOGGER.info("Curso '{}' e seus estudantes foram processados.", course.getDescricao());
    
        return course;
    }
}
