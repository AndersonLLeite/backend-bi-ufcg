package com.ufcg.bi.services.discentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.EntrantGeolocationDTO;
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
    public List<EntrantGeolocationDTO> getAllEntrantGeolocations() {
        List<EntrantGeolocation> geolocations = entrantGeolocationRepository.findAll();
        List<EntrantGeolocationDTO> dtoList = new ArrayList<>();

        for (EntrantGeolocation geolocation : geolocations) {
            for (Map.Entry<String, Double> entry : geolocation.getGeolocationDistribution().entrySet()) {
                String[] locationParts = entry.getKey().split(" - ");
                String estado = locationParts.length > 0 ? locationParts[0] : "";
                String cidade = locationParts.length > 1 ? locationParts[1] : "";
                
                EntrantGeolocationDTO dto = new EntrantGeolocationDTO(
                    geolocation.getId(),
                    geolocation.getCodigoDoCurso(),
                    geolocation.getNomeCurso(),
                    geolocation.getStatus(),
                    geolocation.getCodigoDoSetor(),
                    geolocation.getNomeDoSetor(),
                    geolocation.getCodigoDoCampus(),
                    geolocation.getNomeDoCampus(),
                    geolocation.getPeriodo(),
                    geolocation.getAno(),
                    estado,
                    cidade,
                    entry.getValue().intValue()
                );
                
                dtoList.add(dto);
            }
        }
        return dtoList;
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
