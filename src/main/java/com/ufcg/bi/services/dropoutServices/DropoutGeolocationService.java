package com.ufcg.bi.services.dropoutServices;

import java.util.List;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutGeolocationDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.dropoutModels.DropoutGeolocation;

public interface DropoutGeolocationService {
    public void createDropoutGeolocation(Course course, String term);
    public List<DropoutGeolocationDTO> getAllDropoutGeolocations();
    
}
