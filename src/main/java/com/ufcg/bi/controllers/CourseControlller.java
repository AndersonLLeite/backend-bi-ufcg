package com.ufcg.bi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.services.course.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Arrays;


@RestController
@RequestMapping("/curso")
public class CourseControlller {

    @Autowired
    private CourseService courseService;
    
    @GetMapping("/cursos")
    public List<Course> getAllCourses() {
       return courseService.getAllCourses();
    }
    
    
    
}
