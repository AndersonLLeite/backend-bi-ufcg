package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourses(List<Integer> courseCodes);

    List<Course> fetchCourses();
}
