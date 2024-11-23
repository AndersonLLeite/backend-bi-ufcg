package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCourses(List<Integer> courseCodes);
}
