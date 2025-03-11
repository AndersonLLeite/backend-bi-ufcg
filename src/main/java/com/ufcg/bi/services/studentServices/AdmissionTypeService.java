package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.AdmissionTypeDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface AdmissionTypeService{
    public List<AdmissionTypeDTO> getAllAdmissionTypes();
    public void createAdmissionType(Course course, String term);
    
}