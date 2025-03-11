package com.ufcg.bi.services.campusServices;

import java.util.List;

import com.ufcg.bi.DTO.campusDTOs.DropoutAndEntryCountDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface DropoutAndEntryCountService {
    public List<DropoutAndEntryCountDTO> getAllDropoutAndEntryCount();
    public void createDropoutAndEntryCount( Course course, String term);
  
    
}
