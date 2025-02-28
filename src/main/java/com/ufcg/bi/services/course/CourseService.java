package com.ufcg.bi.services.course;

import com.ufcg.bi.DTO.CourseDTO;
import com.ufcg.bi.models.course.Course;


import java.util.List;

public interface CourseService {
   public  List<Course> fetchCourses();
   public  void processCourse(Course course);
   public List<CourseDTO> getAllCourses();
}
