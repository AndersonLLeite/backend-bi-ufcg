package com.ufcg.bi.services.campus;

import java.util.List;

import com.ufcg.bi.models.campus.StudentCount;
import com.ufcg.bi.models.course.Course;

public interface StudentCountService  {
    public List<StudentCount> getAllStudentCount();
    public void createStudentCount(Course course, String term);
    
}