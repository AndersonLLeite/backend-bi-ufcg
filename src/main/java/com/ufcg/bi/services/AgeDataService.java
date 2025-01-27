package com.ufcg.bi.services;

import java.util.List;

import com.ufcg.bi.models.AgeData;
import com.ufcg.bi.models.Course;

public interface AgeDataService {
    public List<AgeData> getAllAgeData();
    public void createAgeData(Course course, String term);
}
