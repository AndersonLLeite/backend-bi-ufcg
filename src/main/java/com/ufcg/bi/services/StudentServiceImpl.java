package com.ufcg.bi.services;

import com.ufcg.bi.models.Student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private final WebClient webClient;

    public StudentServiceImpl(@Value("${app.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                // Configura o limite do buffer para um valor alto
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize(10 * 1024 * 1024)) // 10 MB
                .build();
    }


    @Override
    public List<Student> fetchStudents(Integer courseCode) {
        Mono<List<Student>> response = webClient.get()
                .uri("/estudantes?curso=" + courseCode)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Student>>() {});

        return response.block();
    }

}
