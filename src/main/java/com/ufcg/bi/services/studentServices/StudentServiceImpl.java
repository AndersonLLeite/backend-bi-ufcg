package com.ufcg.bi.services.studentServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.ufcg.bi.models.studentModels.Student;

import reactor.core.publisher.Mono;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private final WebClient webClient;

   @Autowired
   public StudentServiceImpl(@Value("${app.service.base-url}") String baseUrl) {
    this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .exchangeStrategies(ExchangeStrategies.builder()
                    .codecs(configurer -> configurer.defaultCodecs()
                            .maxInMemorySize(16 * 1024 * 1024)) // 16 MB
                    .build())
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
