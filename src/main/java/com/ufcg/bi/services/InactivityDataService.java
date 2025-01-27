package com.ufcg.bi.services;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.InactivityData;

public interface InactivityDataService {

    public  List<InactivityData> getAllInactivityData();
    public void createInactivityData(Course course, String term);

    
} 