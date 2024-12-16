package com.ufcg.bi.controllers;

import com.ufcg.bi.models.*;
import com.ufcg.bi.services.CourseService;
import com.ufcg.bi.services.DataService;
import com.ufcg.bi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DataService dataService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/filter")
    public ResponseEntity<?> getData(@RequestBody FilterData filter) {
        List<Course> courses = courseService.getCoursesByFilter(filter);
        List<String> terms = filter.getTerms();
        Data data = dataService.getData(courses, terms);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/filter_data")
    public ResponseEntity<?> getFilterData() {
        return ResponseEntity.ok(dataService.getFilterData());
    }

    @GetMapping("/cursos")
    public List<Course> getCourses() {
        return courseService.fetchCourses();
    }

    @GetMapping("/students/{courseCode}")
    public List<Student> getStudents(@PathVariable Integer courseCode) {
        return studentService.fetchStudents(courseCode);
    }

    @GetMapping("/all_data")
    public ResponseEntity<?> getAllData() {
        //pegar todos os cursos
        List<Course> courses = courseService.GetCourses();
        List<String> terms = dataService.getFilterData().getTerms();
        return ResponseEntity.ok(dataService.getData(courses, terms));
    }



}
