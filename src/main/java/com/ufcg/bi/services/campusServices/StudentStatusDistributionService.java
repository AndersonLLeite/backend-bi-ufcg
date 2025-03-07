package com.ufcg.bi.services.campusServices;

import java.util.List;

import com.ufcg.bi.DTO.campusDTOs.StudentStatusDistributionDTO;
import com.ufcg.bi.models.campusModels.StudentStatusDistribution;
import com.ufcg.bi.models.courseModels.Course;

public interface StudentStatusDistributionService {
    public List<StudentStatusDistributionDTO> getAllStudentStatusDistribution();
    public void createStudentStatusDistribution(Course course, String term);
}
