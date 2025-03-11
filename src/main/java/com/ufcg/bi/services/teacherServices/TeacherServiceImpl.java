package com.ufcg.bi.services.teacherServices;

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

import com.ufcg.bi.DTO.teacherDTOs.TeacherDTO;
import com.ufcg.bi.models.TeacherModels.Teacher;
import com.ufcg.bi.repositories.teacherRepositories.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final WebClient webClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(@Value("${app.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
    }

    @Override
    public List<TeacherDTO> GetAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherDTOs.add
            (new TeacherDTO(
                teacher.getTeacherId(),
                teacher.getDepartmentCode(),
                teacher.getStatus(),
                teacher.getDegree(),
                getCentroCode(teacher.getDepartmentCode())
                )
            );
        }
        return teacherDTOs;
    }
    private int getCentroCode(int departmentCode){
        return Integer.parseInt(String.valueOf(departmentCode).substring(0, 1));
    }

   

    @Transactional
    @Retryable(
        value = { WebClientException.class, ResourceAccessException.class }, 
        maxAttempts = 3, 
        backoff = @Backoff(delay = 5000)
    )
    @Override
    public void fetchTeachers() {
        int page = 1;
        int pageSize = 200;
        
        LOGGER.info("Iniciando busca paginada de docentes.");
        
        List<Teacher> allTeachers = new ArrayList<>();
        
        while (true) {
            List<Teacher> teachers = fetchTeachersFromPage(page, pageSize);
            
            if (teachers.isEmpty()) {
                LOGGER.info("Nenhum docente encontrado na página {}. Encerrando busca.", page);
                break;
            }

            LOGGER.info("{} docentes encontrados na página {}.", teachers.size(), page);
            allTeachers.addAll(teachers);

            // Salvar em lotes
            if (allTeachers.size() >= 500) {
                saveTeachersBatch(allTeachers);
                allTeachers.clear(); // Limpa a lista após o batch
            }

            page++;
        }

        // Salva qualquer docente restante
        if (!allTeachers.isEmpty()) {
            saveTeachersBatch(allTeachers);
        }
    }

    private List<Teacher> fetchTeachersFromPage(int page, int pageSize) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/professores")
                            .queryParam("pagina", page)
                            .queryParam("tamanho", pageSize)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Teacher>>() {})
                    .block(); // Pode usar subscribe() para reativo
        } catch (WebClientException e) {
            LOGGER.error("Erro ao buscar docentes na página {}: {}", page, e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveTeachersBatch(List<Teacher> teachers) {
        try {
            teacherRepository.saveAll(teachers);
            LOGGER.info("Foram salvos {} docentes no banco de dados.", teachers.size());
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar docentes: {}", e.getMessage());
        }
    }
}
