package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.DTO.subjectDTOs.SubjectDTO;
import com.ufcg.bi.models.TeacherModels.Teacher;
import com.ufcg.bi.models.subjectModels.Subject;
import com.ufcg.bi.services.subjectServices.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

     @GetMapping("/subjects")
    public List<SubjectDTO> getSubjects() {
        return subjectService.getAllSubjects();
    }
    
}
