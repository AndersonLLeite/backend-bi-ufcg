package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.DisabilitiesDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.DisabilitiesData;

public interface DisabilitiesDataService {
    public List<DisabilitiesDataDTO> getAllDisabilitiesData();
    public void createDisabilitiesData(Course course, String term);
}
