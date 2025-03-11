package com.ufcg.bi.services.dropoutServices;

import java.util.List;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByDisabilityDataDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface DropoutByDisabilityDataService {
    public List<DropoutByDisabilityDataDTO> getAllDropoutByDisabilityData();
    public void createDropoutByDisabilityData(Course course, String term);
    
    
}
