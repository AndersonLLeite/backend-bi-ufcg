package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.discentes.PolicyData;
import com.ufcg.bi.repositories.discentes.PolicyDataRepository;

@Service
public class PolicyDataServiceImpl implements PolicyDataService {

    @Autowired
    private PolicyDataRepository policyDataRepository;
    
    @Override
    public List<PolicyData> getAllPolicyData() {
        return policyDataRepository.findAll();
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
