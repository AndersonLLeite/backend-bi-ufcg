package com.ufcg.bi.services.studentServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.studentDTOs.DisabilitiesDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.DisabilitiesData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.StudentRepositories.DisabilitiesDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DisabilitiesDataServiceImpl implements DisabilitiesDataService {
    @Autowired
    private DisabilitiesDataRepository disabilitiesRepository;
    
    @Override
    public List<DisabilitiesDataDTO> getAllDisabilitiesData() {
        List<DisabilitiesData> disabilitiesData = disabilitiesRepository.findAll();
        List<DisabilitiesDataDTO> disabilitiesDataDTOs = new ArrayList<>();
        for (DisabilitiesData disabilities : disabilitiesData) {
            for (Map.Entry<String, Double> entry : disabilities.getDisabilitiesDistribution().entrySet()) {
                disabilitiesDataDTOs.add(
                    new DisabilitiesDataDTO(
                    disabilities.getId(),
                    disabilities.getCodigoDoCurso(),
                    disabilities.getNomeCurso(),
                    disabilities.getStatus(),
                    disabilities.getCodigoDoSetor(),
                    disabilities.getNomeDoSetor(),
                    disabilities.getCodigoDoCampus(),
                    disabilities.getNomeDoCampus(),
                    disabilities.getPeriodo(),
                    disabilities.getAno(),
                    entry.getKey(),
                    entry.getValue()
                ));
            }
        }
        return disabilitiesDataDTOs;
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
        if (student.getPeriodoDeIngresso() == null ||
                !student.getPeriodoDeIngresso().equals(term) ||
                student.getDeficiencias() == null ||
                student.getDeficiencias().isEmpty()) {
            continue;
        }

        for (String deficiencia : student.getDeficiencias()) {
            disabilitiesDistribution.merge(deficiencia, 1.0, Double::sum);
        }
    }

    return disabilitiesDistribution;
}

    
}