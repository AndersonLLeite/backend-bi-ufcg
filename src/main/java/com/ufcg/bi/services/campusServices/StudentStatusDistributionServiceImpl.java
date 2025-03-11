package com.ufcg.bi.services.campusServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.campusDTOs.StudentStatusDistributionDTO;
import com.ufcg.bi.models.campusModels.StudentStatusDistribution;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.campusRepositories.StudentStatusDistributionRepository;
import com.ufcg.bi.utils.Utils;


@Service
public class StudentStatusDistributionServiceImpl implements StudentStatusDistributionService {
    
    @Autowired
    private StudentStatusDistributionRepository studentStatusDistributionRepository;
    
    @Override
    public List<StudentStatusDistributionDTO> getAllStudentStatusDistribution() {
        List<StudentStatusDistribution> studentStatusData = studentStatusDistributionRepository.findAll();
        List<StudentStatusDistributionDTO> studentStatusDTOs = new ArrayList<>();
        for (StudentStatusDistribution studentStatus : studentStatusData) {
            for (Map.Entry<String, Double> entry : studentStatus.getStudentStatus().entrySet()) {
                StudentStatusDistributionDTO studentStatusDTO = new StudentStatusDistributionDTO(
                    studentStatus.getId(),
                    studentStatus.getCodigoDoCurso(),
                    studentStatus.getNomeCurso(),
                    studentStatus.getStatus(),
                    studentStatus.getCodigoDoSetor(),
                    studentStatus.getNomeDoSetor(),
                    studentStatus.getCodigoDoCampus(),
                    studentStatus.getNomeDoCampus(),
                    studentStatus.getPeriodo(),
                    studentStatus.getAno(),
                    entry.getKey(),
                    entry.getValue()
                );
                studentStatusDTOs.add(studentStatusDTO);
            }
        }
        return studentStatusDTOs;
    }
    
    @Override
    public void createStudentStatusDistribution(Course course, String term) {
        StudentStatusDistribution statusData = new StudentStatusDistribution(
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
            getStudentStatusDistribution(course, term)
        );
        studentStatusDistributionRepository.save(statusData);
    }

    private Map<String, Double> getStudentStatusDistribution(Course course, String term) {
        Map<String, Double> statusDistribution = new HashMap<>();

        for (Student student : course.getStudents()) {
            if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
                continue;
            }

            String status = student.getSituacao() != null ? student.getSituacao() : "Unknown";

            statusDistribution.merge(status, 1.0, Double::sum);
        }

        return statusDistribution;
    }



}
