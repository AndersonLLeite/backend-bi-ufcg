package com.ufcg.bi.services.campus;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.campus.StudentCount;
import com.ufcg.bi.repositories.campus.StudentCountRepository;

@Service
public class StudentCountServiceImpl implements StudentCountService {
    
    @Autowired
    private StudentCountRepository studentCountRepository;
    
    @Override
    public List<StudentCount> getAllStudentCount() {
        return studentCountRepository.findAll();
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
