package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.DTO.teacherDTOs.TeacherDTO;
import com.ufcg.bi.services.teacherServices.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public List<TeacherDTO> getTeachers() {
        return teacherService.GetAllTeachers();
    }
}
