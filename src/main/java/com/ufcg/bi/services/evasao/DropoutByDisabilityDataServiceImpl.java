package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.evasao.DropoutByDisabilityData;
import com.ufcg.bi.repositories.evasao.DropoutByDisabilityDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutByDisabilityDataServiceImpl implements DropoutByDisabilityDataService {
    @Autowired
    private DropoutByDisabilityDataRepository dropoutByDisabilityDataRepository;
    @Override
    public List<DropoutByDisabilityData> getAllDropoutByDisabilityData() {
        return dropoutByDisabilityDataRepository.findAll();
    }
    
    @Override
    public void createDropoutByDisabilityData(Course course, String term) {
        DropoutByDisabilityData dropoutByDisabilityData = new DropoutByDisabilityData(
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
            getEvasionByDisabilities(course, term)
        );
    
        dropoutByDisabilityDataRepository.save(dropoutByDisabilityData);
    }
    
    private Map<String, Double> getEvasionByDisabilities(Course course, String term) {
        Map<String, Double> evasionByDisabilities = new HashMap<>();
    
        if (course.getStudents() == null) return evasionByDisabilities;
    
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
    
            // Verifica se o estudante possui deficiências
            if (student.getDeficiencias() == null || student.getDeficiencias().isEmpty()) {
                continue;
            }
    
            // Atualiza a contagem de evasões por tipo de deficiência
            for (String deficiencia : student.getDeficiencias()) {
                evasionByDisabilities.merge(deficiencia, 1.0, Double::sum);
            }
        }
    
        return evasionByDisabilities;
    }
    }
