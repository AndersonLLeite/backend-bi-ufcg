package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.discentes.AgeAtEnrollment;
import com.ufcg.bi.repositories.discentes.AgeAtEnrollmentRepository;

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
        String ageRange = getAgeRange(String.valueOf(idadeNaMatricula));

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

private String getAgeRange(String age) {
    int idade = Integer.parseInt(age);

    if (idade >= 16 && idade <= 18) {
        return "16-18";
    } else if (idade >= 19 && idade <= 21) {
        return "19-21";
    } else if (idade >= 22 && idade <= 24) {
        return "22-24";
    } else if (idade >= 25 && idade <= 27) {
        return "25-27";
    } else if (idade >= 28 && idade <= 30) {
        return "28-30";
    } else {
        return "31+";
    }
}
  


    
}
