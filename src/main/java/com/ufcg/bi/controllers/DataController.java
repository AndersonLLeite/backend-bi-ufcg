package com.ufcg.bi.controllers;

import com.ufcg.bi.models.*;
import com.ufcg.bi.repositories.AgeDataRepository;
import com.ufcg.bi.repositories.GenderDataRepository;
import com.ufcg.bi.repositories.PolicyDataRepository;
import com.ufcg.bi.services.AgeAtEnrollmentService;
import com.ufcg.bi.services.CourseService;
import com.ufcg.bi.services.DataService2;
import com.ufcg.bi.services.FilterDataService;
import com.ufcg.bi.services.InactivityDataService;
import com.ufcg.bi.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
public class DataController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DataService2 dataService2;

    @Autowired
    private StudentService studentService;

    @Autowired 
    private GenderDataRepository genderRepository;

    @Autowired
    private AgeDataRepository ageRepository;
    
    @Autowired 
    private PolicyDataRepository policyRepository;

    @Autowired
    private InactivityDataService inactivityService;

    @Autowired
    private FilterDataService filterService;

    @Autowired  
    private AgeAtEnrollmentService ageAtEnrollmentService;
    
    @GetMapping("/cursos")
    public List<Course> getCourses() {
        return courseService.fetchCourses();
    }

    @GetMapping("/students/{courseCode}")
    public List<Student> getStudents(@PathVariable Integer courseCode) {
        return studentService.fetchStudents(courseCode);
    }


@GetMapping("/age")
    public List<AgeData> getAgeInstances() {
        return ageRepository.findAll();
    
    }

@GetMapping ("/gender")
public List<GenderData> getGenderInstances() {
    return genderRepository.findAll();
}

@GetMapping("/policy")
public List<PolicyData> getPolicy() {
    return policyRepository.findAll();
}

@GetMapping("/inactivity")
public List<InactivityData> getInactivity() {
    return inactivityService.getAllInactivityData();
}

@GetMapping("/filter")
public List<FilterData> getFilter() {
    return filterService.getAllFilterData();
}

@GetMapping("/age_at_enrollment")
public List<AgeAtEnrollment> getAgeAtEnrollment() {
    return ageAtEnrollmentService.getAllAgeAtEnrollment();

}


}


