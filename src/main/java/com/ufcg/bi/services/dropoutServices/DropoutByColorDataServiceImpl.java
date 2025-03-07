package com.ufcg.bi.services.dropoutServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByColorDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutByColorData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.dropoutRepositories.DropoutByColorDataRepository;
import com.ufcg.bi.utils.Utils;
@Service
public class DropoutByColorDataServiceImpl implements DropoutByColorDataService {
    @Autowired
    private DropoutByColorDataRepository dropoutByColorDataRepository;
  
    @Override
    public List<DropoutByColorDataDTO> getAllDropoutByColorData() {
        List<DropoutByColorData> dropoutByColorData = dropoutByColorDataRepository.findAll();
        List<DropoutByColorDataDTO> dropoutByColorDataDTOs = new ArrayList<>();
        for (DropoutByColorData dropout : dropoutByColorData) {
            for (Map.Entry<String, Double> entry : dropout.getDropoutByColor().entrySet()) {
                DropoutByColorDataDTO dropoutByColorDataDTO = new DropoutByColorDataDTO(
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
                dropoutByColorDataDTOs.add(dropoutByColorDataDTO);
            }
        }
        return dropoutByColorDataDTOs;
        
    }

    @Override
    public void createDropoutByColorData(Course course, String term) {
        DropoutByColorData dropoutByColorData = new DropoutByColorData(
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
            getDropoutByColor(course, term)
        );

        dropoutByColorDataRepository.save(dropoutByColorData);
      
    }
    

    private Map<String, Double> getDropoutByColor(Course course, String term) {
    Map<String, Double> evasionByColor = new HashMap<>();

    if (course.getStudents() == null) return evasionByColor;

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        // Define a cor do estudante ou usa "Desconhecido" caso seja nula
        String cor = student.getCor() != null ? student.getCor() : "Desconhecido";

        // Incrementa a contagem de evasão por cor
        evasionByColor.merge(cor, 1.0, Double::sum);
    }

    return evasionByColor;
}

}
