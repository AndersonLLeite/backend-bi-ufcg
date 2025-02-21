package com.ufcg.bi.services.docentes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.docentes.Teacher;
import com.ufcg.bi.repositories.docentes.TeacherRepository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final WebClient webClient;

    @Autowired
    private TeacherRepository teacherRepository;

     @Autowired
    public TeacherServiceImpl(
            @Value("${app.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public List<Teacher> GetAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void saveTeachers(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
    }

    @Override
    public List<Teacher> fetchTeachers() {
        Mono<List<Teacher>> response = webClient.get()
        .uri("/professores")
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Teacher>>() {});
        return response.block(); 
    }
    
}
