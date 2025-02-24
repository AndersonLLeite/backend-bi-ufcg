package com.ufcg.bi.services.campus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.campus.StudentStatusDistribution;
import com.ufcg.bi.repositories.campus.StudentStatusDistributionRepository;
import com.ufcg.bi.utils.Utils;


@Service
public class StudentStatusDistributionServiceImpl implements StudentStatusDistributionService {
    
    @Autowired
    private StudentStatusDistributionRepository studentStatusDistributionRepository;
    
    @Override
    public List<StudentStatusDistribution> getAllStudentStatusDistribution() {
        return studentStatusDistributionRepository.findAll();
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
            // Verifica se o estudante pertence ao curso e período desejado
            if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
                continue;
            }

            // Determina a situação do estudante, padrão para "Desconhecido" caso seja nulo
            String status = student.getSituacao() != null ? student.getSituacao() : "Unknown";

            // Adiciona ou atualiza a contagem do status no Map
            statusDistribution.merge(status, 1.0, Double::sum);
        }

        return statusDistribution;
    }



}
