package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.discentes.InactivityData;
import com.ufcg.bi.models.evasao.DropoutByAdmissionTypeData;
import com.ufcg.bi.models.evasao.DropoutByAgeData;
import com.ufcg.bi.models.evasao.DropoutByColorData;
import com.ufcg.bi.models.evasao.DropoutByDisabilityData;
import com.ufcg.bi.models.evasao.DropoutByGenderData;
import com.ufcg.bi.models.evasao.DropoutBySecondarySchoolTypeData;
import com.ufcg.bi.models.evasao.DropoutGeolocation;
import com.ufcg.bi.services.discentes.InactivityDataService;
import com.ufcg.bi.services.evasao.DropoutByAdmissionTypeDataService;
import com.ufcg.bi.services.evasao.DropoutByAgeDataService;
import com.ufcg.bi.services.evasao.DropoutByColorDataService;
import com.ufcg.bi.services.evasao.DropoutByDisabilityDataService;
import com.ufcg.bi.services.evasao.DropoutByGenderDataService;
import com.ufcg.bi.services.evasao.DropoutBySecondarySchoolTypeDataService;
import com.ufcg.bi.services.evasao.DropoutGeolocationService;

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
    DropoutBySecondarySchoolTypeDataService dropoutBySecondarySchoolTypeDataService;

    @Autowired
    private InactivityDataService inactivityService;

    @Autowired
    private DropoutGeolocationService dropoutGeolocationService;


    @GetMapping("/inactivity")
    public List<InactivityData> getInactivity() {
    return inactivityService.getAllInactivityData();
}

    @GetMapping("/dropouts_by_color")
    public List<DropoutByColorData> getDropoutsByColor() {
        return dropoutByColorService.getAllDropoutByColorData();
    }
    
    @GetMapping("/dropout_by_gender")
    public List<DropoutByGenderData> getDropoutsByGender() {
        return dropoutByGenderService.getAllDropoutByGenderData();
    }

    @GetMapping("/dropout_by_age")
    public List<DropoutByAgeData> getDropoutsByAge() {
        return dropoutByAgeService.getAllDropoutByAgeData();
    }

    @GetMapping("/dropouts_by_disability")
    public List<DropoutByDisabilityData> getDropoutsByDisability() {
        return dropoutByDisabilityService.getAllDropoutByDisabilityData();
    }

    @GetMapping("/dropouts_by_admission_type")
    public List<DropoutByAdmissionTypeData> getDropoutsByAdmission() {
        return dropoutByAdmissionService.getAllDropoutByAdmissionTypeData();
    }
    
    @GetMapping("/dropouts_by_secondary_school_type")
    public List<DropoutBySecondarySchoolTypeData> getDropoutsBySecondarySchoolType() {
        return dropoutBySecondarySchoolTypeDataService.getAllDropoutBySecondarySchoolTypeData();
    }

    @GetMapping("/dropout_by_geolocation")
    public List<DropoutGeolocation> getDropoutsByGeolocation() {
        return dropoutGeolocationService.getAllDropoutGeolocations();
    }





    
}
