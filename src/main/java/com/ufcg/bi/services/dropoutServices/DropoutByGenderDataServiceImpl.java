package com.ufcg.bi.services.dropoutServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByGenderDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutByGenderData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.dropoutRepositories.DropoutByGenderDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutByGenderDataServiceImpl implements DropoutByGenderDataService {
    @Autowired
    private DropoutByGenderDataRepository dropoutByGenderDataRepository;
    @Override
    public List<DropoutByGenderDataDTO> getAllDropoutByGenderData() {
        List<DropoutByGenderData> dropoutByGenderData = dropoutByGenderDataRepository.findAll();
        List<DropoutByGenderDataDTO> dropoutByGenderDataDTOs = new ArrayList<>();
        for (DropoutByGenderData dropout : dropoutByGenderData) {
            for (Map.Entry<String, Double> entry : dropout.getDropoutByGender().entrySet()) {
                DropoutByGenderDataDTO dropoutByGenderDataDTO = new DropoutByGenderDataDTO(
                    dropout.getId(),
                    dropout.getCodigoDoCurso(),
                    dropout.getNomeCurso(),
                    dropout.getStatus(),
                    dropout.getCodigoDoSetor(),
                    dropout.getNomeDoSetor(),
                    dropout.getCodigoDoCampus(),
                    dropout.getNomeDoCampus(),
                    dropout.getPeriodo(),
                    dropout.getAno(),
                    entry.getKey(),
                    entry.getValue()
                );
                dropoutByGenderDataDTOs.add(dropoutByGenderDataDTO);
            }
        }
        return dropoutByGenderDataDTOs;    
    }

    @Override
    public void createDropoutByGenderData(Course course, String term) {
        DropoutByGenderData dropoutByGenderData = new DropoutByGenderData(
            course.getDescricao() + " - " + term,
            course.getCodigoDoCurso(),
            course.getDescricao(),
            course.getStatus(),
            course.getCodigoDoSetor(),
            course.getNomeDoSetor(),
            course.getCampus(),
            course.getNomeDoCampus(),
            term,
            Utils.getYearFromTerm(term),
            getDropoutByGender(course, term)
        );

        dropoutByGenderDataRepository.save(dropoutByGenderData);
        
    }
    

    private Map<String, Double> getDropoutByGender(Course course, String term) {
    Map<String, Double> evasionByGender = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para análise
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }
        System.out.println("==================================================");
        System.out.println(student.getGenero());

        String genero = student.getGenero() != null ? student.getGenero() : "Desconhecido";

        if (evasionByGender.containsKey(genero)) {
            evasionByGender.put(genero, evasionByGender.get(genero) + 1);
        } else {
            evasionByGender.put(genero, 1.0);
        }
    }

    return evasionByGender;
}


    
}
