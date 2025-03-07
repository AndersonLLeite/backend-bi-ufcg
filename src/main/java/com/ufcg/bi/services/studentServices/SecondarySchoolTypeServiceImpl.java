package com.ufcg.bi.services.studentServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.studentDTOs.SecondarySchoolTypeDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.SecondarySchoolType;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.StudentRepositories.SecondarySchoolTypeRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class SecondarySchoolTypeServiceImpl implements SecondarySchoolTypeService {

    @Autowired
    private SecondarySchoolTypeRepository secondarySchoolTypeRepository;

    @Override
    public List<SecondarySchoolTypeDTO> getAllSecondarySchoolTypes() {
        List<SecondarySchoolType> secondarySchoolTypes = secondarySchoolTypeRepository.findAll();
        List<SecondarySchoolTypeDTO> secondarySchoolTypeDTOs = new ArrayList<>();
        for (SecondarySchoolType data : secondarySchoolTypes) {
            for (Map.Entry<String, Double> entry : data.getSecondarySchoolType().entrySet()) {
                secondarySchoolTypeDTOs.add(
                    new SecondarySchoolTypeDTO(
                        data.getId(),
                        data.getCodigoDoCurso(),
                        data.getNomeCurso(),
                        data.getStatus(),
                        data.getCodigoDoSetor(),
                        data.getNomeDoSetor(),
                        data.getCodigoDoCampus(),
                        data.getNomeDoCampus(),
                        data.getPeriodo(),
                        data.getAno(),
                        entry.getKey(),
                        entry.getValue()
                    ));
            }
            
        }
        return secondarySchoolTypeDTOs;
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
            Utils.getYearFromTerm(term),
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
