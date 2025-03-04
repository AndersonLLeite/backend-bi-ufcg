package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.evasao.DropoutBySecondarySchoolTypeData;
import com.ufcg.bi.repositories.evasao.DropoutBySecondarySchoolTypeDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutBySecondarySchoolTypeDataServiceImpl implements DropoutBySecondarySchoolTypeDataService {
    @Autowired
    private DropoutBySecondarySchoolTypeDataRepository dropoutBySecondarySchoolTypeDataRepository;
    @Override
    public List<DropoutBySecondarySchoolTypeData> getAllDropoutBySecondarySchoolTypeData() {
        return dropoutBySecondarySchoolTypeDataRepository.findAll();
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
