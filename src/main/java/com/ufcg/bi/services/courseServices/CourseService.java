package com.ufcg.bi.services.courseServices;

import com.ufcg.bi.DTO.courseDTOs.CourseDTO;
import com.ufcg.bi.models.courseModels.Course;

import java.util.List;

public interface CourseService {
   public  void fetchCourses();
   public List<CourseDTO> getAllCourses();
}
