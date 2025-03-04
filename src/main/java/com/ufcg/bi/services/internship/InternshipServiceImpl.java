package com.ufcg.bi.services.internship;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ufcg.bi.DTO.InternshipProviderDTO;
import com.ufcg.bi.models.Internship.Internship;
import com.ufcg.bi.repositories.internship.InternshipRepository;

@Service
public class InternshipServiceImpl implements InternshipService {
    private final WebClient webClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(InternshipServiceImpl.class);

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    public InternshipServiceImpl(@Value("${app.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    private List<Internship> fetchInternshipsPaginated() {
        int page = 1;
        int pageSize = 200;
        List<Internship> allInternships = new ArrayList<>();

        while (true) {
            try {
                int currentPage = page; // Variável final efetiva
                List<Internship> pageInternships = webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/estagios")
                                .queryParam("pagina", currentPage)
                                .queryParam("tamanho", pageSize)
                                .build())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Internship>>() {})
                        .block();

                if (pageInternships == null || pageInternships.isEmpty()) {
                    break; // Sai do loop se não houver mais dados
                }

                allInternships.addAll(pageInternships);
                page++;

            } catch (Exception e) {
                LOGGER.error("Erro ao buscar estágios na página {}: {}", page, e.getMessage());
                break;
            }
        }
        return allInternships;
    }

    // private InternshipProviderDTO fetchProviderById(Integer providerId) {
    //     try {
    //         return webClient.get()
    //                 .uri("/concedentes/" + providerId)
    //                 .retrieve()
    //                 .bodyToMono(InternshipProviderDTO.class)
    //                 .block();
    //     } catch (Exception e) {
    //         LOGGER.error("Erro ao buscar concedente {}: {}", providerId, e.getMessage());
    //         return null;
    //     }
    // }

    @Override
    public void fetchInternships() {
        List<Internship> internships = fetchInternshipsPaginated();
        if (internships.isEmpty()) {
            LOGGER.warn("Nenhum estágio encontrado.");
            return;
        }

        // for (Internship internship : internships) {
        //     InternshipProviderDTO provider = fetchProviderById(internship.getIdConcedente());
        //     if (provider != null) {
        //         internship.setRazao_social(provider.getRazaoSocial());
        //         internship.setMunicipio(provider.getMunicipio());
        //         internship.setOrgao_administracao_publica(provider.getOrgaoAdministracaoPublica());
        //     }
        // }

        internshipRepository.saveAll(internships);
        LOGGER.info("Foram salvos {} estágios no banco.", internships.size());
    }
}