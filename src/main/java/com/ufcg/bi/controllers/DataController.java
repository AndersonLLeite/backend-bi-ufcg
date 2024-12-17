package com.ufcg.bi.controllers;

import com.ufcg.bi.models.*;
import com.ufcg.bi.services.CourseService;
import com.ufcg.bi.services.DataService;
import com.ufcg.bi.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        List<Course> courses = courseService.GetCourses();
        List<String> terms = dataService.getFilterData().getTerms();
        return ResponseEntity.ok(dataService.getData(courses, terms));
    }

    @GetMapping("/courses_json")
    public ResponseEntity<?> getCoursesJson() {
        List<Map<String, Object>> response = new ArrayList<>();

        // Exemplos de dados fictícios. Substituir pelos dados reais.
        Map<String, Object> courseData = new HashMap<>();
        courseData.put("codigo_do_curso", 101);
        courseData.put("descricao", "Curso de Engenharia de Computação");
        courseData.put("status", "Ativo");
        courseData.put("codigo_do_setor", 15);
        courseData.put("nome_do_setor", "Centro de Engenharia Elétrica e Informática");
        courseData.put("grau_do_curso", "Bacharelado");
        courseData.put("campus", 1);
        courseData.put("nome_do_campus", "Campina Grande");

        List<Map<String, Object>> periodos = new ArrayList<>();
        Map<String, Object> periodoData = new HashMap<>();
        periodoData.put("periodo", "2020.1");
        periodoData.put("gender_distribution", Map.of("male", 60.0, "female", 40.0));
        periodoData.put("age_distribution", Map.of("18-20", 50.0, "21-25", 40.0, "26+", 10.0));
        periodoData.put("affirmative_policy_distribution", Map.of("yes", 30.0, "no", 70.0));
        // Adicionar outros campos conforme necessário.
        periodos.add(periodoData);

        courseData.put("periodos", periodos);

        response.add(courseData);

        return ResponseEntity.ok(response);
    }
}
