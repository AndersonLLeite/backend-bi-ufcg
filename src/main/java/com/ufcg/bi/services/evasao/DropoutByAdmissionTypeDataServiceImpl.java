package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.evasao.DropoutByAdmissionTypeData;
import com.ufcg.bi.repositories.evasao.DropoutByAdmissionTypeDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutByAdmissionTypeDataServiceImpl implements DropoutByAdmissionTypeDataService {
    @Autowired
    DropoutByAdmissionTypeDataRepository dropoutByAdmissionTypeDataRepository;
    @Override
    public List<DropoutByAdmissionTypeData> getAllDropoutByAdmissionTypeData() {
        return dropoutByAdmissionTypeDataRepository.findAll();
    }



    @Override
    public void createDropoutByAdmissionTypeData(Course course, String term) {
        DropoutByAdmissionTypeData dropoutByAdmissionTypeData = new DropoutByAdmissionTypeData(
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
            getEvasionByAdmissionType(course, term)
        );

        dropoutByAdmissionTypeDataRepository.save(dropoutByAdmissionTypeData);
        
    }

    private Map<String, Double> getEvasionByAdmissionType(Course course, String term) {
    Map<String, Double> evasionByAdmissionType = new HashMap<>();

    if (course.getStudents() == null) return evasionByAdmissionType;

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

        // Determina a forma de ingresso, usando "Desconhecido" como padrão
        String formaDeIngresso = student.getFormaDeIngresso() != null 
                ? student.getFormaDeIngresso() 
                : "Desconhecido";

        // Atualiza o contador para a forma de ingresso
        evasionByAdmissionType.merge(formaDeIngresso, 1.0, Double::sum);
    }

    return evasionByAdmissionType;
}

    
}
