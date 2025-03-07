package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.EntrantGeolocationDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.EntrantGeolocation;

public interface EntrantGeolocationService {
    public void createEntrantGeolocation(Course course, String term);
    public List<EntrantGeolocationDTO> getAllEntrantGeolocations();   
}
