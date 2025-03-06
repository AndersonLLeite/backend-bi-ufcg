package com.ufcg.bi.services.evasao;

import java.util.List;

import com.ufcg.bi.DTO.DropoutGeolocationDTO;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.evasao.DropoutGeolocation;

public interface DropoutGeolocationService {
    public void createDropoutGeolocation(Course course, String term);
    public List<DropoutGeolocationDTO> getAllDropoutGeolocations();
    
}
