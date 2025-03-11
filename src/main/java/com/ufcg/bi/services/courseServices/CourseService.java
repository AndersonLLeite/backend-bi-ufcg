package com.ufcg.bi.services.courseServices;

import com.ufcg.bi.DTO.courseDTOs.CourseDTO;

import java.util.List;

public interface CourseService {
   public  void fetchCourses();
   public List<CourseDTO> getAllCourses();
}
