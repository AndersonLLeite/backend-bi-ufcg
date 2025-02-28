package com.ufcg.bi.controllers;

import com.ufcg.bi.models.*;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.AgeAtEnrollment;
import com.ufcg.bi.models.discentes.DisabilitiesData;
import com.ufcg.bi.models.discentes.GenderData;
import com.ufcg.bi.models.discentes.PolicyData;
import com.ufcg.bi.models.discentes.SecondarySchoolType;
import com.ufcg.bi.repositories.discentes.GenderDataRepository;
import com.ufcg.bi.repositories.discentes.PolicyDataRepository;
import com.ufcg.bi.services.StudentService;
import com.ufcg.bi.services.course.CourseService;
import com.ufcg.bi.services.discentes.AgeAtEnrollmentService;
import com.ufcg.bi.services.discentes.DisabilitiesDataService;
import com.ufcg.bi.services.discentes.SecondarySchoolTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/discentes")
public class DiscentesController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired 
    private GenderDataRepository genderRepository;


    
    @Autowired 
    private PolicyDataRepository policyRepository;

    @Autowired  
    private AgeAtEnrollmentService ageAtEnrollmentService;

    @Autowired
    private DisabilitiesDataService disabilitiesDataService;

    @Autowired
    private SecondarySchoolTypeService secondarySchoolTypeService;


    
    @GetMapping("/cursos")
    public List<Course> getCourses() {
        return courseService.fetchCourses();
    }

    @GetMapping("/students/{courseCode}")
    public List<Student> getStudents(@PathVariable Integer courseCode) {
        return studentService.fetchStudents(courseCode);
    }

@GetMapping ("/gender")
public List<GenderData> getGenderInstances() {
    return genderRepository.findAll();
}

@GetMapping("/policy")
public List<PolicyData> getPolicy() {
    return policyRepository.findAll();
}



@GetMapping("/age_at_enrollment")
public List<AgeAtEnrollment> getAgeAtEnrollment() {
    return ageAtEnrollmentService.getAllAgeAtEnrollment();

}

@GetMapping("/disabilities")
public List<DisabilitiesData> getDisabilities() {
    return disabilitiesDataService.getAllDisabilitiesData();
}

@GetMapping("/secondary_school_type")
public List<SecondarySchoolType> getSecondarySchoolType() {
    return secondarySchoolTypeService.getAllSecondarySchoolTypes();
}

@GetMapping("/admission_type")
public String getAdmissionType() {
    return new String();
}



}


