package com.ufcg.bi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.DTO.CourseDTO;
import com.ufcg.bi.services.course.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseControlller {

    @Autowired
    private CourseService courseService;
    
    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses() {
       return courseService.getAllCourses();
    }
    
    
    
}
