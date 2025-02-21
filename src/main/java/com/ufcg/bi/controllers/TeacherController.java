package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.docentes.Teacher;
import com.ufcg.bi.services.docentes.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherService.GetAllTeachers();
    }
}
