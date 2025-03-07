package com.ufcg.bi.services.campusServices;

import java.util.List;

import com.ufcg.bi.DTO.campusDTOs.StudentCenterDistributionDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface StudentCenterDistributionService {
    public List<StudentCenterDistributionDTO> getAllStudentCenterDistribution();
    public void createStudentCenterDistribution(Course course, String term);
}
