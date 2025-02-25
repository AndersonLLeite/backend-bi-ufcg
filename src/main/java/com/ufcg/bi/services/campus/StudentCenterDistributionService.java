package com.ufcg.bi.services.campus;

import java.util.List;

import com.ufcg.bi.models.campus.StudentCenterDistribution;
import com.ufcg.bi.models.course.Course;

public interface StudentCenterDistributionService {
    public List<StudentCenterDistribution> getAllStudentCenterDistribution();
    public void createStudentCenterDistribution(Course course, String term);
}
