package com.ufcg.bi.services;

import com.ufcg.bi.models.*;
import com.ufcg.bi.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    private final WebClient webClient;

    public CourseServiceImpl() {
        this.webClient = WebClient.builder().baseUrl("https://eureca.sti.ufcg.edu.br/das/v2").build();
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
    public List<Course> getCoursesByFilter(FilterData filter) {
        // Extrair os IDs dos filtros
        List<Integer> centros = (filter.getCentros() == null || filter.getCentros().isEmpty())
                ? null
                // Mapear os centros para seus respectivos IDs em Integer
                : filter.getCentros().stream().map(Centro::getId).map(Long::intValue).toList();

        List<Integer> campus = (filter.getCampus() == null || filter.getCampus().isEmpty())
                ? null
                : filter.getCampus().stream().map(Campus::getId).map(Long::intValue).toList();

        List<Integer> cursos = (filter.getCursos() == null || filter.getCursos().isEmpty())
                ? null
                : filter.getCursos().stream().map(Curso::getId).map(Long::intValue).toList();

        // Executar a consulta com base nos filtros
        return courseRepository.findCoursesByFilters(centros, campus, cursos);
    }
}
