package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.DTO.campusDTOs.DropoutAndEntryCountDTO;
import com.ufcg.bi.DTO.campusDTOs.StudentCenterDistributionDTO;
import com.ufcg.bi.DTO.campusDTOs.StudentCountDTO;
import com.ufcg.bi.DTO.campusDTOs.StudentStatusDistributionDTO;
import com.ufcg.bi.services.campusServices.DropoutAndEntryCountService;
import com.ufcg.bi.services.campusServices.StudentCenterDistributionService;
import com.ufcg.bi.services.campusServices.StudentCountService;
import com.ufcg.bi.services.campusServices.StudentStatusDistributionService;

@RestController
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    private StudentCenterDistributionService studentCenterDistributionService;

    @Autowired
    private StudentStatusDistributionService studentStatusDistributionService;

    @Autowired
    private StudentCountService studentCountService;

    @Autowired
    private DropoutAndEntryCountService dropoutAndEntryCountService;

    @GetMapping("/student_center_distribution")
    public List<StudentCenterDistributionDTO> getStudentCenterDistribution() {
        return studentCenterDistributionService.getAllStudentCenterDistribution();
    }

    @GetMapping("/student_status_distribution")
    public List<StudentStatusDistributionDTO> getStudentStatusDistribution() {
        return studentStatusDistributionService.getAllStudentStatusDistribution();
    }

    @GetMapping("/student_count")
    public List<StudentCountDTO> getStudentCount() {
        return studentCountService.getAllStudentCount();
    }

    @GetMapping("/dropout_and_entry_count")
    public List<DropoutAndEntryCountDTO> getDropoutAndEntryCount() {
        return dropoutAndEntryCountService.getAllDropoutAndEntryCount();
    }


    
}
