package com.ufcg.bi.services.course;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface CourseService {
   public  List<Course> fetchCourses();
   public  void processCourse(Course course);
   public List<Course> getAllCourses();

}
