package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.evasao.DropoutByGenderData;
import com.ufcg.bi.repositories.evasao.DropoutByGenderDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutByGenderDataServiceImpl implements DropoutByGenderDataService {
    @Autowired
    private DropoutByGenderDataRepository dropoutByGenderDataRepository;
    @Override
    public List<DropoutByGenderData> getAllDropoutByGenderData() {
        return dropoutByGenderDataRepository.findAll();     
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

        // Determina o gênero, usando "Desconhecido" como padrão
        String genero = student.getGenero() != null ? student.getGenero() : "Desconhecido";

        evasionByGender.put(genero, evasionByGender.getOrDefault(genero, 0.0) + 1);
    }

    return evasionByGender;
}


    
}
