package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.evasao.DropoutByAgeData;
import com.ufcg.bi.models.evasao.DropoutByColorData;
import com.ufcg.bi.models.evasao.DropoutByGenderData;
import com.ufcg.bi.services.evasao.DropoutByAgeDataService;
import com.ufcg.bi.services.evasao.DropoutByColorDataService;
import com.ufcg.bi.services.evasao.DropoutByGenderDataService;
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

    @GetMapping("/dropouts_by_color")
    public List<DropoutByColorData> getDropoutsByColor() {
        return dropoutByColorService.getAllDropoutByColorData();
    }
    
    @GetMapping("/dropout_by_gender")
    public List<DropoutByGenderData> getDropoutsByGender() {
        return dropoutByGenderService.getAllDropoutByGenderData();
    }

    @GetMapping("/dropput_by_age")
    public List<DropoutByAgeData> getDropoutsByAge() {
        return dropoutByAgeService.getAllDropoutByAgeData();
    }



    
}
