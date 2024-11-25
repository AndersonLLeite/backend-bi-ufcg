package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.FilterData;

import java.util.List;

public interface CourseService {
    public List<Course> getCourses(List<Integer> courseCodes);

    List<Course> fetchCourses();
    List<Course> getCoursesByFilter (FilterData filter);
}
