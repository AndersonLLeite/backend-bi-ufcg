package com.ufcg.bi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.GenderData;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.repositories.GenderDataRepository;

@Service
public class GenderDataServiceImpl implements GenderDataService{
    @Autowired
    private GenderDataRepository genderRepository;

    @Override
    public List<GenderData> getAllGenderData() {
        return genderRepository.findAll();
    }

    @Override
    public void createGenderData(Course course, String term) {
        GenderData genderData = new GenderData(
            course.getDescricao() + " - " + term,
            course.getCodigoDoCurso(),
            course.getDescricao(),
            course.getStatus(),
            course.getCodigoDoSetor(),
            course.getNomeDoSetor(),
            course.getCampus(),
            course.getNomeDoCampus(),
            term,
            getGenderDistribution(course, term)

            
        );
        genderRepository.save(genderData);
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

   
}
