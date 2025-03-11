package com.ufcg.bi.services.campusServices;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.campusDTOs.StudentCountDTO;
import com.ufcg.bi.models.campusModels.StudentCount;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.campusRepositories.StudentCountRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class StudentCountServiceImpl implements StudentCountService {
    
    @Autowired
    private StudentCountRepository studentCountRepository;
    
    @Override
    public List<StudentCountDTO> getAllStudentCount() {
        List<StudentCount> studentCountData = studentCountRepository.findAll();
        List<StudentCountDTO> studentCountDTOs = new ArrayList<>();
        for (StudentCount studentCount : studentCountData) {
            StudentCountDTO studentCountDTO = new StudentCountDTO(
                studentCount.getId(),
                studentCount.getCodigoDoCurso(),
                studentCount.getNomeCurso(),
                studentCount.getStatus(),
                studentCount.getCodigoDoSetor(),
                studentCount.getNomeDoSetor(),
                studentCount.getCodigoDoCampus(),
                studentCount.getNomeDoCampus(),
                studentCount.getPeriodo(),
                studentCount.getAno(),
                studentCount.getQuantidade()
               
                
                
            );
            studentCountDTOs.add(studentCountDTO);
        }
        return studentCountDTOs;
    }
    
    @Override
    public void createStudentCount(Course course, String term) {
        StudentCount studentCountData = new StudentCount(
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
                countStudents(course, term)
        );

        studentCountRepository.save(studentCountData);
    }

    private int countStudents(Course course, String term) {
        int count = 0;

        for (Student student : course.getStudents()) {
            // Conta apenas os estudantes que ingressaram no período desejado
            if (student.getPeriodoDeIngresso() != null && student.getPeriodoDeIngresso().equals(term)) {
                count++;
            }
        }

        return count;
    }
}
