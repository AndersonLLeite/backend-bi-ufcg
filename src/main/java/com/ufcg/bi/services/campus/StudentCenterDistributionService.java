package com.ufcg.bi.services.campus;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.campus.StudentCenterDistribution;

public interface StudentCenterDistributionService {
    public List<StudentCenterDistribution> getAllStudentCenterDistribution();
    public void createStudentCenterDistribution(Course course, String term);
}
