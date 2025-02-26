package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.GenderData;
import com.ufcg.bi.repositories.discentes.GenderDataRepository;
import com.ufcg.bi.utils.Utils;

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
            Utils.getYearFromTerm(term),
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
    
            String genero = student.getGenero() != null ? student.getGenero() : "Desconhecido";
    
            genderDistribution.putIfAbsent(genero, 0.0);
            genderDistribution.put(genero, genderDistribution.get(genero) + 1);
        }
    
        return genderDistribution;
    }
    

   
}
