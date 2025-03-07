package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.InactivityDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.InactivityData;

public interface InactivityDataService {

    public  List<InactivityDataDTO> getAllInactivityData();
    public void createInactivityData(Course course, String term);

    
} 