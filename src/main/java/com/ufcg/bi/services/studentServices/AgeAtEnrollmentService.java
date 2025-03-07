package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.AgeAtEnrollmenteDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.AgeAtEnrollment;

public interface AgeAtEnrollmentService {
    public List<AgeAtEnrollmenteDTO> getAllAgeAtEnrollment();
    public void createAgeAtEnrollment(Course course, String term);

    
} 