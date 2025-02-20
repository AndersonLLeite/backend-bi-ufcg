package com.ufcg.bi.services.campus;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.campus.StudentCount;

public interface StudentCountService  {
    public List<StudentCount> getAllStudentCount();
    public void createStudentCount(Course course, String term);
    
}