package com.ufcg.bi.services.dropoutServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByDisabilityDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutByDisabilityData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.dropoutRepositories.DropoutByDisabilityDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutByDisabilityDataServiceImpl implements DropoutByDisabilityDataService {
    @Autowired
    private DropoutByDisabilityDataRepository dropoutByDisabilityDataRepository;
    @Override
    public List<DropoutByDisabilityDataDTO> getAllDropoutByDisabilityData() {
        List<DropoutByDisabilityData> dropoutByDisabilityData = dropoutByDisabilityDataRepository.findAll();
        List<DropoutByDisabilityDataDTO> dropoutByDisabilityDataDTOs = new ArrayList<>();
        for (DropoutByDisabilityData dropout : dropoutByDisabilityData) {
            for (Map.Entry<String, Double> entry : dropout.getDropoutByDisabilitie().entrySet()) {
                DropoutByDisabilityDataDTO dropoutByDisabilityDataDTO = new DropoutByDisabilityDataDTO(
                    dropout.getId(),
                    dropout.getCodigoDoCurso(),
                    dropout.getNomeCurso(),
                    dropout.getStatus(),
                    dropout.getCodigoDoSetor(),
                    dropout.getNomeDoSetor(),
                    dropout.getCodigoDoCampus(),
                    dropout.getNomeDoCampus(),
                    dropout.getPeriodo(),
                    dropout.getAno(),
                    entry.getKey(),
                    entry.getValue()
                );
                dropoutByDisabilityDataDTOs.add(dropoutByDisabilityDataDTO);
            }
        }
        return dropoutByDisabilityDataDTOs;
    }
    
    @Override
    public void createDropoutByDisabilityData(Course course, String term) {
        DropoutByDisabilityData dropoutByDisabilityData = new DropoutByDisabilityData(
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
            getEvasionByDisabilities(course, term)
        );
    
        dropoutByDisabilityDataRepository.save(dropoutByDisabilityData);
    }
    
    private Map<String, Double> getEvasionByDisabilities(Course course, String term) {
        Map<String, Double> evasionByDisabilities = new HashMap<>();
    
        if (course.getStudents() == null) return evasionByDisabilities;
    
        for (Student student : course.getStudents()) {
            if (student.getPeriodoDeEvasao() == null ||
                    !term.equals(student.getPeriodoDeEvasao()) ||
                    "ATIVO".equals(student.getSituacao())) {
                continue;
            }
    
            if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                    "REGULAR".equals(student.getMotivoDeEvasao())) {
                continue;
            }
    
            if (student.getDeficiencias() == null || student.getDeficiencias().isEmpty()) {
                continue;
            }
    
            for (String deficiencia : student.getDeficiencias()) {
                evasionByDisabilities.merge(deficiencia, 1.0, Double::sum);
            }
        }
    
        return evasionByDisabilities;
    }
    }
