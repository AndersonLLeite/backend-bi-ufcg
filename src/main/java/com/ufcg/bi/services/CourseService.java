package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface CourseService {
   public  List<Course> fetchCourses();
   public  Course processCourse(Course course);
    

}
