package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.EntrantGeolocation;
import com.ufcg.bi.repositories.discentes.EntrantGeolocationRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class EntrantGeolocationServiceImpl implements EntrantGeolocationService {

    @Autowired
    private EntrantGeolocationRepository entrantGeolocationRepository;

    @Override
    public void createEntrantGeolocation(Course course, String term) {
        EntrantGeolocation entrantGeolocation = new EntrantGeolocation(
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
            getEntrantGeolocationDistribuition(course, term)
        );

        entrantGeolocationRepository.save(entrantGeolocation);
        
    }

    @Override
    public List<EntrantGeolocation> getAllEntrantGeolocations() {
        return entrantGeolocationRepository.findAll();
    }

    private  Map<String, Double> getEntrantGeolocationDistribuition(Course course, String term) {
        Map<String, Double> entrantGeolocationDistribuition = new HashMap<>();

        for (Student student : course.getStudents()) {
            if (student.getPeriodoDeIngresso() == null ||
                    !student.getPeriodoDeIngresso().equals(term) ||
                    student.getLocalDeNascimento() == null ||
                    student.getNaturalidade() == null) {
                continue;
            }

            String geolocation = student.getNaturalidade() + " - " + student.getLocalDeNascimento();
            entrantGeolocationDistribuition.merge(geolocation, 1.0, Double::sum);
        }

        return entrantGeolocationDistribuition;
        
    }
    
}
