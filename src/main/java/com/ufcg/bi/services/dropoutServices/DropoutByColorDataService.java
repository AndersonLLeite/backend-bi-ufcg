package com.ufcg.bi.services.dropoutServices;

import java.util.List;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByColorDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutByColorData;

public interface DropoutByColorDataService {
    public List<DropoutByColorDataDTO> getAllDropoutByColorData();
    public void createDropoutByColorData(Course course, String term);
}