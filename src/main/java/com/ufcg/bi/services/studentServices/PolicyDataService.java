package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.PolicyDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.PolicyData;

public interface PolicyDataService {
    public List<PolicyDataDTO> getAllPolicyData();
    public void createPolicyData(Course course, String term);
}
