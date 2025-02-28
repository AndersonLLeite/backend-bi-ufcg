package com.ufcg.bi.services.subjectService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ufcg.bi.models.subject.Subject;
import com.ufcg.bi.repositories.subject.SubjectRepository;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final WebClient webClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    public SubjectServiceImpl(@Value("${app.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
    }

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public void saveSubjects(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
    }

    @Transactional
    @Override
    public void fetchSubjects() {
        try {
            int page = 1;
            int pageSize = 200;
            List<Subject> allSubjects = new ArrayList<>();
            int currentPage = page;
            while (true) {
                List<Subject> subjects = webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/disciplinas")
                                .queryParam("pagina", currentPage)
                                .queryParam("tamanho", pageSize)
                                .build())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Subject>>() {})
                        .block();

                if (subjects == null || subjects.isEmpty()) {
                    break;
                }

                allSubjects.addAll(subjects);
                page++;
            }

            if (!allSubjects.isEmpty()) {
                subjectRepository.saveAll(allSubjects);
                LOGGER.info("Foram salvas {} disciplinas no banco.", allSubjects.size());
            } else {
                LOGGER.warn("Nenhuma disciplina encontrada para salvar.");
            }
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar disciplinas: {}", e.getMessage());
        }
    }
}
