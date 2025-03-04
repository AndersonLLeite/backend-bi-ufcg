package com.ufcg.bi.services.docentes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ufcg.bi.models.docentes.Teacher;
import com.ufcg.bi.repositories.docentes.TeacherRepository;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final WebClient webClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);


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
public void fetchTeachersByCampusCode(Integer campusCode) {
    try {
        Mono<List<Teacher>> teachers = this.webClient.get()
                .uri("/professores?campus=" + campusCode)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Teacher>>() {});

        teachers.subscribe(teacherList -> {
            // Atribuir o campusCode a cada professor antes de salvar
            teacherList.forEach(teacher -> teacher.setCampusCode(campusCode));
            saveTeachers(teacherList);
        });

    } catch (Exception e) {
        LOGGER.error("Erro ao buscar docentes: {}", e.getMessage());
    }
}
    
}
