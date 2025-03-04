package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.AdmissionType;
import com.ufcg.bi.repositories.discentes.AdmissionTypeRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class AdmissionTypeServiceImpl  implements AdmissionTypeService {
    @Autowired
    private AdmissionTypeRepository admissionTypeRepository;

    @Override
    public List<AdmissionType> getAllAdmissionTypes() {
        return admissionTypeRepository.findAll();
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
