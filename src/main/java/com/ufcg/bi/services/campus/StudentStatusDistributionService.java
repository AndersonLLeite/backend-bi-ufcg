package com.ufcg.bi.services.campus;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.campus.StudentStatusDistribution;

public interface StudentStatusDistributionService {
    public List<StudentStatusDistribution> getAllStudentStatusDistribution();
    public void createStudentStatusDistribution(Course course, String term);
}
