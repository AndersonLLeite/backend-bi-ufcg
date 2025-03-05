package com.ufcg.bi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.models.FilterData;
import com.ufcg.bi.services.filter.FilterDataService;

@RestController
@RequestMapping("/filter")

public class FilterController {
        @Autowired
    private FilterDataService filterService;

    @GetMapping("/filter")
public List<FilterData> getFilter() {
    return filterService.getAllFilterData();
}
}
