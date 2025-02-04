package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.discentes.InactivityData;

public interface InactivityDataService {

    public  List<InactivityData> getAllInactivityData();
    public void createInactivityData(Course course, String term);

    
} 