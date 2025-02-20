package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.campus.DropoutAndEntryCount;
import com.ufcg.bi.models.campus.StudentCenterDistribution;
import com.ufcg.bi.models.campus.StudentCount;
import com.ufcg.bi.models.campus.StudentStatusDistribution;
import com.ufcg.bi.services.campus.DropoutAndEntryCountService;
import com.ufcg.bi.services.campus.StudentCenterDistributionService;
import com.ufcg.bi.services.campus.StudentCountService;
import com.ufcg.bi.services.campus.StudentStatusDistributionService;

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
    public List<StudentCenterDistribution> getStudentCenterDistribution() {
        return studentCenterDistributionService.getAllStudentCenterDistribution();
    }

    @GetMapping("/student_status_distribution")
    public List<StudentStatusDistribution> getStudentStatusDistribution() {
        return studentStatusDistributionService.getAllStudentStatusDistribution();
    }

    @GetMapping("/student_count")
    public List<StudentCount> getStudentCount() {
        return studentCountService.getAllStudentCount();
    }

    @GetMapping("/dropout_and_entry_count")
    public List<DropoutAndEntryCount> getDropoutAndEntryCount() {
        return dropoutAndEntryCountService.getAllDropoutAndEntryCount();
    }


    
}
