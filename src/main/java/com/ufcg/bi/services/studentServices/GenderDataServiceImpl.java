package com.ufcg.bi.services.studentServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.studentDTOs.GenderDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.GenderData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.StudentRepositories.GenderDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class GenderDataServiceImpl implements GenderDataService{
    @Autowired
    private GenderDataRepository genderRepository;

    @Override
    public List<GenderDataDTO> getAllGenderData() {
        List<GenderData> genderData = genderRepository.findAll();
        List<GenderDataDTO> genderDataDTOs = new ArrayList<>();
        for (GenderData data : genderData) {
            for (Map.Entry<String, Double> entry : data.getSexo().entrySet()) {
                genderDataDTOs.add(
                    new GenderDataDTO(
                        data.getId(),
                        data.getCodigoDoCurso(),
                        data.getCurso(),
                        data.getStatus(),
                        data.getCodigoDoSetor(),
                        data.getSetor(),
                        data.getCodigoDoCampus(),
                        data.getCampus(),
                        data.getPeriodo(),
                        data.getAno(),
                        entry.getKey(),
                        entry.getValue()
                    ));
            }
            
        }
        return genderDataDTOs;
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
    
            if (genderDistribution.containsKey(genero)) {
                genderDistribution.put(genero, genderDistribution.get(genero) + 1);
            } else {
                genderDistribution.put(genero, 1.0);
            }
        }
    
        return genderDistribution;
    }
    

   
}
