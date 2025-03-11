package com.ufcg.bi.services.filterServices;

import java.util.List;

import com.ufcg.bi.DTO.filterDtos.FilterDataDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface FilterDataService {
    public List<FilterDataDTO> getAllFilterData();
    public void createFilterData(Course course, String term);
    
}
