package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.EntrantGeolocationDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface EntrantGeolocationService {
    public void createEntrantGeolocation(Course course, String term);
    public List<EntrantGeolocationDTO> getAllEntrantGeolocations();   
}
