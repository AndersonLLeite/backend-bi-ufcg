package com.ufcg.bi.services;

import java.util.List;

import com.ufcg.bi.models.FilterData;
import com.ufcg.bi.models.course.Course;

public interface FilterDataService {
    public List<FilterData> getAllFilterData();
    public void createFilterData(Course course, String term);
    
}
