package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.discentes.InactivityData;
import com.ufcg.bi.repositories.discentes.InactivityRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class InactivityDataServiceImpl implements InactivityDataService {
    @Autowired
    private InactivityRepository inactivityRepository;

    @Override
    public List<InactivityData> getAllInactivityData() {
        return inactivityRepository.findAll();
    }

    @Override
    public void createInactivityData(Course course, String term) {
        InactivityData inactivityData = new InactivityData(
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
            getInactivityReasonDistribution(course, term)
        );

        inactivityRepository.save(inactivityData);
    }

    private Map<String, Double> getInactivityReasonDistribution(Course course, String term) {
    Map<String, Double> inactivityReasonDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null ||
                student.getPeriodoDeEvasao() == null ||
                !student.getPeriodoDeIngresso().equals(student.getPeriodoDeEvasao()) ||
                !student.getPeriodoDeIngresso().equals(term) ||
                student.getMotivoDeEvasao() == null) {
            continue;
        }

        inactivityReasonDistribution.put(
            student.getMotivoDeEvasao(), 
            inactivityReasonDistribution.getOrDefault(student.getMotivoDeEvasao(), 0.0) + 1
        );
    }

    return inactivityReasonDistribution;
}
    
}
