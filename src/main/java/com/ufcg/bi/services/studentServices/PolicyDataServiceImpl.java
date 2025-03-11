package com.ufcg.bi.services.studentServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.studentDTOs.PolicyDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.PolicyData;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.StudentRepositories.PolicyDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class PolicyDataServiceImpl implements PolicyDataService {

    @Autowired
    private PolicyDataRepository policyDataRepository;
    
    @Override
    public List<PolicyDataDTO> getAllPolicyData() {
        List<PolicyData> policyData = policyDataRepository.findAll();
        List<PolicyDataDTO> policyDataDTOs = new ArrayList<>();
        for (PolicyData data : policyData) {
            for (Map.Entry<String, Double> entry : data.getPoliticaAfirmativa().entrySet()) {
                policyDataDTOs.add(
                    new PolicyDataDTO(
                        data.getId() ,
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
        return policyDataDTOs;
    }
    
    @Override
    public void createPolicyData(Course course, String term) {
        PolicyData policyData = new PolicyData(
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
            getAffirmativePolicyDistribution(course, term)
        );
        
        policyDataRepository.save(policyData);
    }

    private Map<String, Double> getAffirmativePolicyDistribution(Course course, String term) {
    Map<String, Double> affirmativePolicyDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !term.equals(student.getPeriodoDeIngresso())) {
            continue;
        }
        String policy = student.getPoliticaAfirmativa();
        affirmativePolicyDistribution.putIfAbsent(policy, 0.0);
        affirmativePolicyDistribution.put(policy, affirmativePolicyDistribution.get(policy) + 1);
    }

    return affirmativePolicyDistribution;
}

    
    
}
