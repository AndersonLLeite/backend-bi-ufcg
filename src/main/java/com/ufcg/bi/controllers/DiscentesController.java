package com.ufcg.bi.controllers;

import com.ufcg.bi.models.*;
import com.ufcg.bi.models.discentes.AgeAtEnrollment;
import com.ufcg.bi.models.discentes.DisabilitiesData;
import com.ufcg.bi.models.discentes.GenderData;
import com.ufcg.bi.models.discentes.InactivityData;
import com.ufcg.bi.models.discentes.PolicyData;
import com.ufcg.bi.repositories.discentes.AgeDataRepository;
import com.ufcg.bi.repositories.discentes.GenderDataRepository;
import com.ufcg.bi.repositories.discentes.PolicyDataRepository;
import com.ufcg.bi.services.FilterDataService;
import com.ufcg.bi.services.StudentService;
import com.ufcg.bi.services.discentes.AgeAtEnrollmentService;
import com.ufcg.bi.services.discentes.CourseService;
import com.ufcg.bi.services.discentes.DisabilitiesDataService;
import com.ufcg.bi.services.discentes.InactivityDataService;
import com.ufcg.bi.services.DataService2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
@RequestMapping("/discentes")
public class DiscentesController {

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
    private AgeAtEnrollmentService ageAtEnrollmentService;

    @Autowired
    private DisabilitiesDataService disabilitiesDataService;


    
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

@GetMapping("/age_at_enrollment")
public List<AgeAtEnrollment> getAgeAtEnrollment() {
    return ageAtEnrollmentService.getAllAgeAtEnrollment();

}

@GetMapping("/disabilities")
public List<DisabilitiesData> getDisabilities() {
    return disabilitiesDataService.getAllDisabilitiesData();
}


}


