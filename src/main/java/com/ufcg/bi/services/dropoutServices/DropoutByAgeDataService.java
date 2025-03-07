package com.ufcg.bi.services.dropoutServices;

import java.util.List;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByAgeDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutByAgeData;

public interface DropoutByAgeDataService {
    public List<DropoutByAgeDataDTO> getAllDropoutByAgeData();
    public void createDropoutByAgeData(Course course, String term);
}
