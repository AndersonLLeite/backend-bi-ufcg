package com.ufcg.bi.services.campus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.campus.StudentCenterDistribution;
import com.ufcg.bi.repositories.campus.StudentCenterDistributionRepository;
import com.ufcg.bi.utils.Utils;


@Service
public class StudentCenterDistributionServiceImpl implements StudentCenterDistributionService {
    
    @Autowired
    private StudentCenterDistributionRepository centerDistributionRepository;
    
    @Override
    public List<StudentCenterDistribution> getAllStudentCenterDistribution() {
        return centerDistributionRepository.findAll();
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
            // Verifica se o estudante pertence ao curso e período desejado
            if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
                continue;
            }

            // Determina o centro do estudante, padrão para "Desconhecido" caso seja nulo
            String center = student.getNomeDoSetor() != null ? student.getNomeDoSetor() : "Desconhecido";

            // Adiciona ou atualiza a contagem do centro no Map
            centerDistribution.merge(center, 1.0, Double::sum);
        }

        return centerDistribution;
    }

    
}
