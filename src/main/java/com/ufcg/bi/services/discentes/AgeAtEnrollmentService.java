package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.AgeAtEnrollment;

public interface AgeAtEnrollmentService {
    public List<AgeAtEnrollment> getAllAgeAtEnrollment();
    public void createAgeAtEnrollment(Course course, String term);

    
} 