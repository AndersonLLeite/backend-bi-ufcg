package com.ufcg.bi.services.dropoutServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutGeolocationDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutGeolocation;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.dropoutRepositories.DropoutGeolocationRepository;
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
    public List<DropoutGeolocationDTO> getAllDropoutGeolocations() {
         List<DropoutGeolocation> geolocations = dropoutGeolocationRepository.findAll();
        List<DropoutGeolocationDTO> dtoList = new ArrayList<>();

        for (DropoutGeolocation geolocation : geolocations) {
            for (Map.Entry<String, Double> entry : geolocation.getDropoutGeolocationDistributions().entrySet()) {
                String[] locationParts = entry.getKey().split(" - ");
                String estado = locationParts.length > 0 ? locationParts[0] : "";
                String cidade = locationParts.length > 1 ? locationParts[1] : "";
                
                DropoutGeolocationDTO dto = new DropoutGeolocationDTO(
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
    
    private Map<String, Double> getDropoutGeolocationDistribuition(Course course, String term) {
        Map<String, Double> dropoutGeolocationDistribuition = new HashMap<>();

        for (Student student : course.getStudents()) {
            if (student.getPeriodoDeEvasao() == null ||
                    !student.getPeriodoDeEvasao().equals(term) ||
                    student.getLocalDeNascimento() == null ||
                    student.getNaturalidade() == null) {
                continue;
            }
            if ("ATIVO".equals(student.getSituacao()) ||
                "GRADUADO".equals(student.getMotivoDeEvasao()) || 
            "REGULAR".equals(student.getMotivoDeEvasao())) {
        continue;
    }

            String geolocation = student.getNaturalidade() + " - " + student.getLocalDeNascimento();
            dropoutGeolocationDistribuition.merge(geolocation, 1.0, Double::sum);
        }
        return dropoutGeolocationDistribuition;
    }
}
