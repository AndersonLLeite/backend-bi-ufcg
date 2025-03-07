package com.ufcg.bi.services.studentServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.studentDTOs.AdmissionTypeDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.AdmissionType;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.StudentRepositories.AdmissionTypeRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class AdmissionTypeServiceImpl  implements AdmissionTypeService {
    @Autowired
    private AdmissionTypeRepository admissionTypeRepository;

    @Override
    public List<AdmissionTypeDTO> getAllAdmissionTypes() {
        List<AdmissionType> admissionTypes = admissionTypeRepository.findAll();
        List<AdmissionTypeDTO> admissionTypeDTOs = new ArrayList<>();
        for (AdmissionType admissionType : admissionTypes) {
            for (Map.Entry<String, Double> entry : admissionType.getAdmissionTypeDistribution().entrySet()) {
                admissionTypeDTOs.add(
                    new AdmissionTypeDTO(
                    admissionType.getId(),
                    admissionType.getCodigoDoCurso(),
                    admissionType.getNomeCurso(),
                    admissionType.getStatus(),
                    admissionType.getCodigoDoSetor(),
                    admissionType.getNomeDoSetor(),
                    admissionType.getCodigoDoCampus(),
                    admissionType.getNomeDoCampus(),
                    admissionType.getPeriodo(),
                    admissionType.getAno(),
                    entry.getKey(),
                    entry.getValue()
                ));
            }
        }
        return admissionTypeDTOs;
    }

    @Override
    public void createAdmissionType(Course course, String term) {

        AdmissionType data = new AdmissionType(
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
            getAdmissionTypeDistribution(course, term)
        );

        admissionTypeRepository.save(data);
        
    }

    private Map<String, Double> getAdmissionTypeDistribution(Course course, String term) {
        Map<String, Double> admissionTypeDistribution = new HashMap<>();

        for (Student student : course.getStudents()) {
            if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
                continue;
            }

            String admissionType = student.getFormaDeIngresso();
            admissionTypeDistribution.merge(admissionType, 1.0, Double::sum);

        }

        return admissionTypeDistribution;
    }
    
}
