package com.ufcg.bi.services.campus;

import java.util.List;

import com.ufcg.bi.models.campus.StudentStatusDistribution;
import com.ufcg.bi.models.course.Course;

public interface StudentStatusDistributionService {
    public List<StudentStatusDistribution> getAllStudentStatusDistribution();
    public void createStudentStatusDistribution(Course course, String term);
}
