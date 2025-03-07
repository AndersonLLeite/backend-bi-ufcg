package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByAdmissionTypeDataDTO;
import com.ufcg.bi.DTO.dropoutDTOs.DropoutByAgeDataDTO;
import com.ufcg.bi.DTO.dropoutDTOs.DropoutByColorDataDTO;
import com.ufcg.bi.DTO.dropoutDTOs.DropoutByDisabilityDataDTO;
import com.ufcg.bi.DTO.dropoutDTOs.DropoutByGenderDataDTO;
import com.ufcg.bi.DTO.dropoutDTOs.DropoutGeolocationDTO;
import com.ufcg.bi.DTO.dropoutDTOs.DropoutBySecondarySchoolTypeDataDTO;
import com.ufcg.bi.models.dropoutModels.DropoutByAdmissionTypeData;
import com.ufcg.bi.models.dropoutModels.DropoutByAgeData;
import com.ufcg.bi.models.dropoutModels.DropoutByColorData;
import com.ufcg.bi.models.dropoutModels.DropoutByDisabilityData;
import com.ufcg.bi.models.dropoutModels.DropoutByGenderData;
import com.ufcg.bi.models.dropoutModels.DropoutBySecondarySchoolTypeData;
import com.ufcg.bi.models.dropoutModels.DropoutGeolocation;
import com.ufcg.bi.models.studentModels.InactivityData;
import com.ufcg.bi.services.dropoutServices.DropoutByAdmissionTypeDataService;
import com.ufcg.bi.services.dropoutServices.DropoutByAgeDataService;
import com.ufcg.bi.services.dropoutServices.DropoutByColorDataService;
import com.ufcg.bi.services.dropoutServices.DropoutByDisabilityDataService;
import com.ufcg.bi.services.dropoutServices.DropoutByGenderDataService;
import com.ufcg.bi.services.dropoutServices.DropoutBySecondarySchoolTypeDataService;
import com.ufcg.bi.services.dropoutServices.DropoutGeolocationService;
import com.ufcg.bi.services.studentServices.InactivityDataService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/dropout")
public class DropoutController {

    @Autowired
    private DropoutByColorDataService dropoutByColorService;

    @Autowired
    private DropoutByGenderDataService dropoutByGenderService;

    @Autowired
    private DropoutByAgeDataService dropoutByAgeService;

    @Autowired
    private DropoutByDisabilityDataService dropoutByDisabilityService;

    @Autowired
    private DropoutByAdmissionTypeDataService dropoutByAdmissionService;

    @Autowired
    private DropoutBySecondarySchoolTypeDataService dropoutBySecondarySchoolTypeDataService;


    @Autowired
    private DropoutGeolocationService dropoutGeolocationService;

    @GetMapping("/dropouts_by_color")
    public List<DropoutByColorDataDTO> getDropoutsByColor() {
        return dropoutByColorService.getAllDropoutByColorData();
    }
    
    @GetMapping("/dropout_by_gender")
    public List<DropoutByGenderDataDTO> getDropoutsByGender() {
        return dropoutByGenderService.getAllDropoutByGenderData();
    }

    @GetMapping("/dropout_by_age")
    public List<DropoutByAgeDataDTO> getDropoutsByAge() {
        return dropoutByAgeService.getAllDropoutByAgeData();
    }

    @GetMapping("/dropouts_by_disability")
    public List<DropoutByDisabilityDataDTO> getDropoutsByDisability() {
        return dropoutByDisabilityService.getAllDropoutByDisabilityData();
    }

    @GetMapping("/dropouts_by_admission_type")
    public List<DropoutByAdmissionTypeDataDTO> getDropoutsByAdmission() {
        return dropoutByAdmissionService.getAllDropoutByAdmissionTypeData();
    }
    
    @GetMapping("/dropouts_by_secondary_school_type")
    public List<DropoutBySecondarySchoolTypeDataDTO> getDropoutsBySecondarySchoolType() {
        return dropoutBySecondarySchoolTypeDataService.getAllDropoutBySecondarySchoolTypeData();
    }

    @GetMapping("/dropout_by_geolocation")
    public List<DropoutGeolocationDTO> getDropoutsByGeolocation() {
        return dropoutGeolocationService.getAllDropoutGeolocations();
    }





    
}
