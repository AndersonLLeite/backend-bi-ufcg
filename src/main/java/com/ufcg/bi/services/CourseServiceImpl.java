package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final WebClient webClient;

    @Autowired
    public CourseServiceImpl(
            CourseRepository courseRepository,
            @Value("${app.service.base-url}") String baseUrl) {
        this.courseRepository = courseRepository;
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public List<Course> getCourses(List<Integer> courseCodes) {
        // Recupera os cursos com base nos filtros fornecidos
        return courseRepository.findByCodigoDoCursoIn(courseCodes);
    }

    @Override
    public List<Course> fetchCourses() {
        // Faz uma chamada HTTP GET para buscar os cursos
        Mono<List<Course>> response = webClient.get()
                .uri("/cursos")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Course>>() {});
        return response.block(); // Bloqueia o thread para obter o resultado
    }
}
