package com.ufcg.bi.services.campusServices;

import java.util.List;

import com.ufcg.bi.DTO.campusDTOs.StudentCountDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface StudentCountService  {
    public List<StudentCountDTO> getAllStudentCount();
    public void createStudentCount(Course course, String term);
    
}