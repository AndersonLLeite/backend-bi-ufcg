package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.AgeAtEnrollment;
import com.ufcg.bi.repositories.discentes.AgeAtEnrollmentRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class AgeAtEnrollmentServiceImpl implements AgeAtEnrollmentService {

    @Autowired
    private AgeAtEnrollmentRepository ageAtEnrollmentRepository;

    @Override
    public List<AgeAtEnrollment> getAllAgeAtEnrollment() {
        return ageAtEnrollmentRepository.findAll();
    }

    @Override
    public void createAgeAtEnrollment(Course course, String term) {
        AgeAtEnrollment ageAtEnrollment = new AgeAtEnrollment(
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
            getAgeAtEnrollmentDistribution(course, term)
        );

        ageAtEnrollmentRepository.save(ageAtEnrollment);
    }

    private Map<String, Double> getAgeAtEnrollmentDistribution(Course course, String term) {
    Map<String, Double> ageDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
            continue;
        }

        int idadeNaMatricula = calcularIdadeNaMatricula(Integer.parseInt(student.getIdade()), student.getPeriodoDeIngresso());
        String ageRange = Utils.getAgeRange(String.valueOf(idadeNaMatricula));

        ageDistribution.merge(ageRange, 1.0, Double::sum);
    }

    return ageDistribution;
}

private int calcularIdadeNaMatricula(int idadeAtual, String periodoIngresso) {
    String[] partesPeriodo = periodoIngresso.split("\\.");
    int anoIngresso = Integer.parseInt(partesPeriodo[0]);
    int anoAtual = java.time.LocalDate.now().getYear();
    int diferencaAnos = anoAtual - anoIngresso;
    int idadeNaMatricula = idadeAtual - diferencaAnos;

    return idadeNaMatricula;
}


  


    
}
