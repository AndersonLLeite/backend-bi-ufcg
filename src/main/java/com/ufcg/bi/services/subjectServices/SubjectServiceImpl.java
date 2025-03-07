package com.ufcg.bi.services.subjectServices;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.retry.annotation.Backoff;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.bi.DTO.subjectDTOs.SubjectDTO;
import com.ufcg.bi.models.subjectModels.Subject;
import com.ufcg.bi.repositories.subjectRepositories.SubjectRepository;

import jakarta.transaction.Transactional;


@Service
public class SubjectServiceImpl implements SubjectService {
    private final WebClient webClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);
    
    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(@Value("${app.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOs = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectDTOs.add
            (new SubjectDTO(
                subject.getCodigoDaDisciplina(),
                subject.getNome(),
                subject.getCodigoDoSetor(),
                subject.getNomeDoSetor(),
                subject.getCampus(),
                subject.getNomeDoCampus(),
                subject.getStatus(),
                subject.getTipoDeComponenteCurricular()

                )
            );
        }
        return subjectDTOs;
    }

    @Transactional
    @Retryable(
        value = { WebClientException.class, ResourceAccessException.class }, 
        maxAttempts = 3, 
        backoff = @Backoff(delay = 5000)
    )
    @Override
    public void fetchSubjects() {
        int page = 1;
        int pageSize = 200;

        LOGGER.info("Iniciando busca paginada de disciplinas.");
        
        List<Subject> allSubjects = new ArrayList<>();
        
        while (true) {
            List<Subject> subjects = fetchSubjectsFromPage(page, pageSize);
            
            if (subjects.isEmpty()) {
                LOGGER.info("Nenhuma disciplina encontrada na página {}. Encerrando busca.", page);
                break;
            }

            LOGGER.info("{} disciplinas encontradas na página {}.", subjects.size(), page);
            allSubjects.addAll(subjects);

            // Salvar em lotes
            if (allSubjects.size() >= 500) {
                saveSubjectsBatch(allSubjects);
                allSubjects.clear(); // Limpa a lista após o batch
            }

            page++;
        }

        // Salva qualquer disciplina restante
        if (!allSubjects.isEmpty()) {
            saveSubjectsBatch(allSubjects);
        }
    }

    private List<Subject> fetchSubjectsFromPage(int page, int pageSize) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/disciplinas")
                            .queryParam("pagina", page)
                            .queryParam("tamanho", pageSize)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Subject>>() {})
                    .block(); // Pode usar subscribe() para reativo
        } catch (WebClientException e) {
            LOGGER.error("Erro ao buscar disciplinas na página {}: {}", page, e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveSubjectsBatch(List<Subject> subjects) {
        try {
            subjectRepository.saveAll(subjects);
            LOGGER.info("Foram salvas {} disciplinas no banco de dados.", subjects.size());
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar disciplinas: {}", e.getMessage());
        }
    }
}
