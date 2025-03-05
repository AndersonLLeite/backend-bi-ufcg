package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.evasao.DropoutGeolocation;
import com.ufcg.bi.repositories.evasao.DropoutGeolocationRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutGeolocationServiceImpl implements DropoutGeolocationService {

    @Autowired
    private DropoutGeolocationRepository dropoutGeolocationRepository;

    @Override
    public void createDropoutGeolocation(Course course, String term) {
        DropoutGeolocation dropoutGeolocation = new DropoutGeolocation(
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
            getDropoutGeolocationDistribuition(course, term)
        );

        dropoutGeolocationRepository.save(dropoutGeolocation);
    }

    @Override
    public List<DropoutGeolocation> getAllDropoutGeolocations() {
        return dropoutGeolocationRepository.findAll();
    }
    
    private Map<String, Double> getDropoutGeolocationDistribuition(Course course, String term) {
        Map<String, Double> dropoutGeolocationDistribuition = new HashMap<>();

        for (Student student : course.getStudents()) {
            if (student.getPeriodoDeEvasao() == null ||
                    !student.getPeriodoDeEvasao().equals(term) ||
                    student.getLocalDeNascimento() == null ||
                    student.getNaturalidade() == null) {
                continue;
            }

            String geolocation = student.getNaturalidade() + " - " + student.getLocalDeNascimento();
            dropoutGeolocationDistribuition.merge(geolocation, 1.0, Double::sum);
        }
        return dropoutGeolocationDistribuition;
    }
}
