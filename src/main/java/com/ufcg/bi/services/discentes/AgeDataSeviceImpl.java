package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.AgeData;
import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.repositories.discentes.AgeDataRepository;

@Service
public class AgeDataSeviceImpl implements AgeDataService {

    @Autowired
    private AgeDataRepository ageRepository;

    @Override
    public List<AgeData> getAllAgeData() {
        return ageRepository.findAll();
    }

    @Override
    public void createAgeData(Course course, String term) {
        AgeData ageData = new AgeData(
                            
        course.getDescricao() + " - " + term,
            
        course.getCodigoDoCurso(),
        course.getDescricao(),
        course.getStatus(),
        course.getCodigoDoSetor(),
        course.getNomeDoSetor(),
        course.getCampus(),
        course.getNomeDoCampus(),
        term,
        getAgeDistribution(course, term)
                        );
        
        ageRepository.save(ageData);
    }

    private Map<String, Double> getAgeDistribution(Course course, String term) {
    Map<String, Double> ageDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !term.equals(student.getPeriodoDeIngresso())) {
            continue;
        }
        String ageRange = getAgeRange(student.getIdade());
        ageDistribution.putIfAbsent(ageRange, 0.0);
        ageDistribution.put(ageRange, ageDistribution.get(ageRange) + 1);
    }

    return ageDistribution;
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
