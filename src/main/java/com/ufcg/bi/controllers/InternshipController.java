package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.Internship.Internship;
import com.ufcg.bi.services.internship.InternshipService;

@RestController
@RequestMapping("/internship")
public class InternshipController {
    @Autowired
    private InternshipService internshipService;

    @GetMapping("/internships")
    public List<Internship> getInternships() {
        return internshipService.getAllInternships();
    }
    
}
