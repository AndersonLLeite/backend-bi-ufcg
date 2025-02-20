package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.discentes.SecondarySchoolType;
import com.ufcg.bi.repositories.discentes.SecondarySchoolTypeRepository;

@Service
public class SecondarySchoolTypeServiceImpl implements SecondarySchoolTypeService {

    @Autowired
    private SecondarySchoolTypeRepository secondarySchoolTypeRepository;

    @Override
    public List<SecondarySchoolType> getAllSecondarySchoolTypes() {
        return secondarySchoolTypeRepository.findAll();
    }

    @Override
    public void createSecondarySchoolType(Course course, String term) {
        SecondarySchoolType secondarySchoolType = new SecondarySchoolType(
            course.getDescricao() + " - " + term,
            course.getCodigoDoCurso(),
            course.getDescricao(),
            course.getStatus(),
            course.getCodigoDoSetor(),
            course.getNomeDoSetor(),
            course.getCampus(),
            course.getNomeDoCampus(),
            term,
            getSecondarySchoolType(course, term)
        );

        secondarySchoolTypeRepository.save(secondarySchoolType);
    }

    private Map<String, Double> getSecondarySchoolType(Course course, String term) {
    Map<String, Double> secondarySchoolType = new HashMap<>();

    if (course.getStudents() == null) return secondarySchoolType;

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios
        if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
            continue;
        }

        // Determina o tipo de ensino médio, usando "Desconhecido" como padrão
        String tipoDeEnsinoMedio = student.getTipoDeEnsinoMedio() != null 
                ? student.getTipoDeEnsinoMedio() 
                : "Desconhecido";

        // Atualiza o contador para o tipo de ensino médio
        secondarySchoolType.merge(tipoDeEnsinoMedio, 1.0, Double::sum);
    }

    return secondarySchoolType;
}
    

  

    
}
