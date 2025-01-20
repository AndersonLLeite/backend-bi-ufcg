package com.ufcg.bi.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Data2;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.repositories.DataRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService2 {

    private final DataRepository dataRepository;

    public DataService2(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Transactional
    public void saveAll(List<Data2> dataList) {
        dataRepository.saveAll(dataList);
    }

    public List<Data2> getAllData() {
        return dataRepository.findAll();
    }
    
    public Data2 createData(Course courseProcessed, String term) {
        Map<String, Double> genderDistribution = getGenderDistribution(courseProcessed, term);
        Map<String, Double> ageDistribution = getAgeDistribution(courseProcessed, term);
        Map<String, Double> affirmativePolicyDistribution = getAffirmativePolicyDistribution(courseProcessed, term);
        Data2 data = new Data2(null, courseProcessed.getCodigoDoCurso(), courseProcessed.getDescricao(), courseProcessed.getStatus(), courseProcessed.getCodigoDoSetor(), courseProcessed.getNomeDoSetor(), courseProcessed.getCampus(), courseProcessed.getNomeDoCampus(), term, genderDistribution, ageDistribution, affirmativePolicyDistribution);
        return data;
    }

    private Map<String, Double> getGenderDistribution(Course course, String term) {
    Map<String, Double> genderDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !term.equals(student.getPeriodoDeIngresso())) {
            continue;
        }
        genderDistribution.putIfAbsent(student.getGenero(), 0.0);
        genderDistribution.put(student.getGenero(), genderDistribution.get(student.getGenero()) + 1);
    }

    return genderDistribution;
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

private Map<String, Double> getAffirmativePolicyDistribution(Course course, String term) {
    Map<String, Double> affirmativePolicyDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !term.equals(student.getPeriodoDeIngresso())) {
            continue;
        }
        String policy = student.getPoliticaAfirmativa();
        affirmativePolicyDistribution.putIfAbsent(policy, 0.0);
        affirmativePolicyDistribution.put(policy, affirmativePolicyDistribution.get(policy) + 1);
    }

    return affirmativePolicyDistribution;
}





}
