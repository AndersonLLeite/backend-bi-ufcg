package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.DTO.EntrantGeolocationDTO;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.EntrantGeolocation;

public interface EntrantGeolocationService {
    public void createEntrantGeolocation(Course course, String term);
    public List<EntrantGeolocationDTO> getAllEntrantGeolocations();   
}
