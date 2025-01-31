package com.ufcg.bi.services;

import java.util.List;

import com.ufcg.bi.models.AgeAtEnrollment;
import com.ufcg.bi.models.Course;

public interface AgeAtEnrollmentService {
    public List<AgeAtEnrollment> getAllAgeAtEnrollment();
    public void createAgeAtEnrollment(Course course, String term);

    
} 