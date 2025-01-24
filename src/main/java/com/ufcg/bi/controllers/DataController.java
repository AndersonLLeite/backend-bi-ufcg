package com.ufcg.bi.controllers;

import com.ufcg.bi.models.*;
import com.ufcg.bi.repositories.AgeRepository;
import com.ufcg.bi.repositories.GenderRepository;
import com.ufcg.bi.repositories.PolicyRepository;
import com.ufcg.bi.services.CourseService;
import com.ufcg.bi.services.DataService2;
import com.ufcg.bi.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class DataController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DataService2 dataService2;

    @Autowired
    private StudentService studentService;

    @Autowired 
    private GenderRepository genderRepository;

    @Autowired
    private AgeRepository ageRepository;
    
    @Autowired 
    private PolicyRepository policyRepository;
    @GetMapping("/cursos")
    public List<Course> getCourses() {
        return courseService.fetchCourses();
    }

    @GetMapping("/students/{courseCode}")
    public List<Student> getStudents(@PathVariable Integer courseCode) {
        return studentService.fetchStudents(courseCode);
    }

// @GetMapping("/data")
//     public List<Data2> getDataInstances() {
//         return dataService2.getAllData();
//     }

@GetMapping("/age")
    public List<Age> getAgeInstances() {
        return ageRepository.findAll();
    
    }

@GetMapping ("/gender")
public List<Gender> getGenderInstances() {
    return genderRepository.findAll();
}

@GetMapping("/policy")
public List<Policy> getPolicy() {
    return policyRepository.findAll();
}
    
    
    public String getMethodName(@RequestParam String param) {
    return new String();
}

}


