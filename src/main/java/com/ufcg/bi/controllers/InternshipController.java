package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.DTO.internshipDTOs.InternshipDTO;
import com.ufcg.bi.services.internshipServices.InternshipService;

@RestController
@RequestMapping("/internship")
public class InternshipController {
    @Autowired
    private InternshipService internshipService;

    @GetMapping("/internships")
    public List<InternshipDTO> getInternships() {
        return internshipService.getAllInternships();
    }
    
}
