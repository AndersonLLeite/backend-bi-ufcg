package com.ufcg.bi.services.course;

import com.ufcg.bi.DTO.CourseDTO;
import com.ufcg.bi.models.course.Course;


import java.util.List;

public interface CourseService {
   public  void fetchCourses();
   public List<CourseDTO> getAllCourses();
}
