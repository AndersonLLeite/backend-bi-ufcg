package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.docentes.Teacher;
import com.ufcg.bi.models.subject.Subject;
import com.ufcg.bi.services.subjectService.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

     @GetMapping("/subjects")
    public List<Subject> getSubjects() {
        return subjectService.getAllSubjects();
    }
    
}
