package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.AdmissionType;

public interface AdmissionTypeService{
    public List<AdmissionType> getAllAdmissionTypes();
    public void createAdmissionType(Course course, String term);
    
}