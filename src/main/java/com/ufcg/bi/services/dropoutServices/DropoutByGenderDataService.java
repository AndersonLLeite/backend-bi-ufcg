package com.ufcg.bi.services.dropoutServices;

import java.util.List;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByGenderDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutByGenderData;

public interface DropoutByGenderDataService {
    public List<DropoutByGenderDataDTO> getAllDropoutByGenderData();
    public void createDropoutByGenderData(Course course, String term);
}
