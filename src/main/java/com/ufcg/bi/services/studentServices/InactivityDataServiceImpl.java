package com.ufcg.bi.services.studentServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.studentDTOs.InactivityDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.InactivityData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.StudentRepositories.InactivityRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class InactivityDataServiceImpl implements InactivityDataService {
    @Autowired
    private InactivityRepository inactivityRepository;

    @Override
    public List<InactivityDataDTO> getAllInactivityData() {
        List<InactivityData> inactivityData = inactivityRepository.findAll();
        List<InactivityDataDTO> inactivityDataDTOs = new ArrayList<>();
        for (InactivityData data : inactivityData) {
            for (Map.Entry<String, Double> entry : data.getInactivityReasonDistribution().entrySet()) {
                inactivityDataDTOs.add(
                    new InactivityDataDTO(
                        data.getId(),
                        data.getCodigoDoCurso(),
                        data.getCurso(),
                        data.getStatus(),
                        data.getCodigoDoSetor(),
                        data.getSetor(),
                        data.getCodigoDoCampus(),
                        data.getCampus(),
                        data.getPeriodo(),
                        data.getAno(),
                        entry.getKey(),
                        entry.getValue()
                    ));
            }
            
        }
        return inactivityDataDTOs;
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
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                student.getMotivoDeEvasao() == null ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }


        inactivityReasonDistribution.merge(student.getMotivoDeEvasao(), 1.0, Double::sum);
     
    }

    return inactivityReasonDistribution;
}
    
}
