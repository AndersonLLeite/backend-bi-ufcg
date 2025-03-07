package com.ufcg.bi.services.dropoutServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutBySecondarySchoolTypeDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutBySecondarySchoolTypeData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.dropoutRepositories.DropoutBySecondarySchoolTypeDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutBySecondarySchoolTypeDataServiceImpl implements DropoutBySecondarySchoolTypeDataService {
    @Autowired
    private DropoutBySecondarySchoolTypeDataRepository dropoutBySecondarySchoolTypeDataRepository;
    @Override
    public List<DropoutBySecondarySchoolTypeDataDTO> getAllDropoutBySecondarySchoolTypeData() {
        List<DropoutBySecondarySchoolTypeData> dropoutBySecondarySchoolTypeData = dropoutBySecondarySchoolTypeDataRepository.findAll();
        List<DropoutBySecondarySchoolTypeDataDTO> dropoutBySecondarySchoolTypeDataDTOs = new ArrayList<>();
        for (DropoutBySecondarySchoolTypeData dropout : dropoutBySecondarySchoolTypeData) {
            for (Map.Entry<String, Double> entry : dropout.getDropoutBySecondarySchoolType().entrySet()) {
                DropoutBySecondarySchoolTypeDataDTO dropoutBySecondarySchoolTypeDataDTO = new DropoutBySecondarySchoolTypeDataDTO(
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
                dropoutBySecondarySchoolTypeDataDTOs.add(dropoutBySecondarySchoolTypeDataDTO);
            }
        }
        return dropoutBySecondarySchoolTypeDataDTOs;
    }

    @Override
    public void createDropoutBySecondarySchoolTypeData(Course course, String term) {
        DropoutBySecondarySchoolTypeData dropoutBySecondarySchoolTypeData = new DropoutBySecondarySchoolTypeData(
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
            getEvasionBySecondarySchoolType(course, term)
        );

        dropoutBySecondarySchoolTypeDataRepository.save(dropoutBySecondarySchoolTypeData);
    }
    
    private Map<String, Double> getEvasionBySecondarySchoolType(Course course, String term) {
    Map<String, Double> evasionBySecondarySchoolType = new HashMap<>();

    if (course.getStudents() == null) return evasionBySecondarySchoolType;

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

        // Determina o tipo de ensino médio, usando "Desconhecido" como padrão
        String tipoDeEnsinoMedio = student.getTipoDeEnsinoMedio() != null 
                ? student.getTipoDeEnsinoMedio() 
                : "Desconhecido";

        // Atualiza o contador para o tipo de ensino médio
        evasionBySecondarySchoolType.merge(tipoDeEnsinoMedio, 1.0, Double::sum);
    }

    return evasionBySecondarySchoolType;
}
}
