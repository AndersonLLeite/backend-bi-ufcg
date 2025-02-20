package com.ufcg.bi.services;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.FilterData;

public interface FilterDataService {
    public List<FilterData> getAllFilterData();
    public void createFilterData(Course course, String term);
    
}
