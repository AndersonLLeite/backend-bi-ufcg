package com.ufcg.bi.services.internshipServices;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.ufcg.bi.DTO.internshipDTOs.InternshipDTO;
import com.ufcg.bi.models.InternshipModels.Internship;
import com.ufcg.bi.repositories.internshipRepositories.InternshipRepository;

import jakarta.transaction.Transactional;

@Service
public class InternshipServiceImpl implements InternshipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternshipServiceImpl.class);

    private final WebClient webClient;
    private final InternshipRepository internshipRepository;

    @Autowired
    public InternshipServiceImpl(@Value("${app.service.base-url}") String baseUrl, InternshipRepository internshipRepository) {
        this.webClient = WebClient.builder().baseUrl(baseUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
        this.internshipRepository = internshipRepository;
    }

    @Override
    public List<InternshipDTO> getAllInternships() {
        List<Internship> internships = internshipRepository.findAll();
        List<InternshipDTO> internshipDTOs = new ArrayList<>();
          for (Internship internship : internships) {
            InternshipDTO internshipDTO = new InternshipDTO(
                internship.getId(),
                internship.getIdConcedente(),
                internship.getUfConcedente(),
                internship.getDepartamento(),
                internship.getObrigatorio(),
                internship.getStatus(),
                internship.getBolsaMensal(),
                internship.getAuxilioTransporteDiario()
            );
            internshipDTOs.add(internshipDTO);
        }

        return internshipDTOs;
    }

    @Transactional
    @Retryable(
        value = { WebClientException.class, ResourceAccessException.class },
        maxAttempts = 3,
        backoff = @Backoff(delay = 5000)
    )
    @Override
    public void fetchInternships() {
        LOGGER.info("Iniciando processo de busca paginada de estágios.");

        int page = 1;
        int pageSize = 200;
        List<Internship> allInternships = new ArrayList<>();

        while (true) {
            List<Internship> internships = fetchInternshipsFromPage(page, pageSize);

            if (internships.isEmpty()) {
                LOGGER.info("Nenhum estágio encontrado na página {}. Encerrando busca.", page);
                break;
            }

            LOGGER.info("{} estágios encontrados na página {}.", internships.size(), page);
            allInternships.addAll(internships);

            if (allInternships.size() >= 500) {
                saveInternshipsBatch(allInternships);
                allInternships.clear(); 
            }

            page++;
        }

        if (!allInternships.isEmpty()) {
            saveInternshipsBatch(allInternships);
        }
    }

    private List<Internship> fetchInternshipsFromPage(int page, int pageSize) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/estagios")
                            .queryParam("pagina", page)
                            .queryParam("tamanho", pageSize)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Internship>>() {})
                    .block(); 
        } catch (WebClientException e) {
            LOGGER.error("Erro ao buscar estágios na página {}: {}", page, e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveInternshipsBatch(List<Internship> internships) {
        try {
            internshipRepository.saveAll(internships);
            LOGGER.info("Foram salvos {} estágios no banco de dados.", internships.size());
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar estágios: {}", e.getMessage());
        }
    }
}
