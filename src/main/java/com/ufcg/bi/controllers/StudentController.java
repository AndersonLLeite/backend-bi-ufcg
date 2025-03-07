package com.ufcg.bi.controllers;

import com.ufcg.bi.DTO.studentDTOs.AdmissionTypeDTO;
import com.ufcg.bi.DTO.studentDTOs.AgeAtEnrollmenteDTO;
import com.ufcg.bi.DTO.studentDTOs.ColorDataDTO;
import com.ufcg.bi.DTO.studentDTOs.DisabilitiesDataDTO;
import com.ufcg.bi.DTO.studentDTOs.EntrantGeolocationDTO;
import com.ufcg.bi.DTO.studentDTOs.GenderDataDTO;
import com.ufcg.bi.DTO.studentDTOs.InactivityDataDTO;
import com.ufcg.bi.DTO.studentDTOs.PolicyDataDTO;
import com.ufcg.bi.DTO.studentDTOs.SecondarySchoolTypeDTO;
import com.ufcg.bi.models.studentModels.GenderData;
import com.ufcg.bi.repositories.StudentRepositories.GenderDataRepository;
import com.ufcg.bi.services.studentServices.AdmissionTypeService;
import com.ufcg.bi.services.studentServices.AgeAtEnrollmentService;
import com.ufcg.bi.services.studentServices.ColorDataService;
import com.ufcg.bi.services.studentServices.DisabilitiesDataService;
import com.ufcg.bi.services.studentServices.EntrantGeolocationService;
import com.ufcg.bi.services.studentServices.GenderDataService;
import com.ufcg.bi.services.studentServices.InactivityDataService;
import com.ufcg.bi.services.studentServices.PolicyDataService;
import com.ufcg.bi.services.studentServices.SecondarySchoolTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;




@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired  
    private AgeAtEnrollmentService ageAtEnrollmentService;

    @Autowired
    private DisabilitiesDataService disabilitiesDataService;

    @Autowired
    private SecondarySchoolTypeService secondarySchoolTypeService;

    @Autowired
    private EntrantGeolocationService entrantGeolocationService;

    @Autowired
    private AdmissionTypeService admissionTypeService;

    @Autowired
    private ColorDataService colorDataService;

    @Autowired
    private GenderDataService genderDataService;

    @Autowired
    private InactivityDataService inactivityService;

    @Autowired
    private PolicyDataService policyDataService;

    @Autowired
    private GenderDataRepository genderRepository;

    @GetMapping("/admission_type")
    public List<AdmissionTypeDTO> getAdmissionType() {
        return admissionTypeService.getAllAdmissionTypes();
    }
    
    @GetMapping ("/gender")
    public List<GenderDataDTO> getGenderInstances() {
        return genderDataService.getAllGenderData();
    }
   

    @GetMapping("/policy")
    public List<PolicyDataDTO> getPolicy() {
        return policyDataService.getAllPolicyData();
    }


    @GetMapping("/age_at_enrollment")
    public List<AgeAtEnrollmenteDTO> getAgeAtEnrollment() {
        return ageAtEnrollmentService.getAllAgeAtEnrollment();

    }
       @GetMapping("/inactivity")
    public List<InactivityDataDTO> getInactivity() {
    return inactivityService.getAllInactivityData();
    }

    @GetMapping("/color")
    public List<ColorDataDTO> getColor() {
        return colorDataService.getAllColorData();
    }
    

    @GetMapping("/disabilities")
    public List<DisabilitiesDataDTO> getDisabilities() {
        return disabilitiesDataService.getAllDisabilitiesData();
    }

    @GetMapping("/secondary_school_type")
    public List<SecondarySchoolTypeDTO> getSecondarySchoolType() {
        return secondarySchoolTypeService.getAllSecondarySchoolTypes();
    }


    @GetMapping("/entrant_geolocation")
    public List<EntrantGeolocationDTO> getEntrantGeolocation() {
        return entrantGeolocationService.getAllEntrantGeolocations();

    }

}


