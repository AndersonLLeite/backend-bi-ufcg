package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.discentes.DisabilitiesData;
import com.ufcg.bi.repositories.discentes.DisabilitiesDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DisabilitiesDataServiceImpl implements DisabilitiesDataService {
    @Autowired
    private DisabilitiesDataRepository disabilitiesRepository;
    
    @Override
    public List<DisabilitiesData> getAllDisabilitiesData() {
        return disabilitiesRepository.findAll();
    }


    @Override
    public void createDisabilitiesData(Course course, String term) {
        DisabilitiesData disabilitiesData = new DisabilitiesData(
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
            getDisabilitiesDistribution(course, term)
        );

        disabilitiesRepository.save(disabilitiesData);
    }

    private Map<String, Double> getDisabilitiesDistribution(Course course, String term) {
    Map<String, Double> disabilitiesDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para ser considerado
        if (student.getPeriodoDeIngresso() == null ||
                !student.getPeriodoDeIngresso().equals(term) ||
                student.getDeficiencias() == null ||
                student.getDeficiencias().isEmpty()) {
            continue;
        }

        // Processa cada deficiência do estudante e atualiza o Map
        for (String deficiencia : student.getDeficiencias()) {
            disabilitiesDistribution.merge(deficiencia, 1.0, Double::sum);
        }
    }

    return disabilitiesDistribution;
}

    
}