package com.ufcg.bi.services.campusServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.campusDTOs.StudentCenterDistributionDTO;
import com.ufcg.bi.models.campusModels.StudentCenterDistribution;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.campusRepositories.StudentCenterDistributionRepository;
import com.ufcg.bi.utils.Utils;


@Service
public class StudentCenterDistributionServiceImpl implements StudentCenterDistributionService {
    
    @Autowired
    private StudentCenterDistributionRepository centerDistributionRepository;
    
    @Override
    public List<StudentCenterDistributionDTO> getAllStudentCenterDistribution() {
        List<StudentCenterDistribution> centerData = centerDistributionRepository.findAll();
        List<StudentCenterDistributionDTO> dtoList = new ArrayList<>();

        for (StudentCenterDistribution center : centerData) {
            for (Map.Entry<String, Double> entry : center.getCentro().entrySet()) {
                StudentCenterDistributionDTO dto = new StudentCenterDistributionDTO(
                    center.getId(),
                    center.getCodigoDoCurso(),
                    center.getNomeCurso(),
                    center.getStatus(),
                    center.getCodigoDoSetor(),
                    center.getNomeDoSetor(),
                    center.getCodigoDoCampus(),
                    center.getNomeDoCampus(),
                    center.getPeriodo(),
                    center.getAno(),
                    entry.getKey(),
                    entry.getValue()
                );

                dtoList.add(dto);
            }
        }

        return dtoList;
    }
    
    @Override
    public void createStudentCenterDistribution(Course course, String term) {
        StudentCenterDistribution centerData = new StudentCenterDistribution(
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
            getStudentCenterDistribution(course, term)
        );

        centerDistributionRepository.save(centerData);
    }

    private Map<String, Double> getStudentCenterDistribution(Course course, String term) {
        Map<String, Double> centerDistribution = new HashMap<>();

        for (Student student : course.getStudents()) {
            if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
                continue;
            }

            String center = student.getNomeDoSetor() != null ? student.getNomeDoSetor() : "Desconhecido";

            centerDistribution.merge(center, 1.0, Double::sum);
        }

        return centerDistribution;
    }

    
}
