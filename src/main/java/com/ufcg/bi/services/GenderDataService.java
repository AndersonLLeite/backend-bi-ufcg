package com.ufcg.bi.services;

import java.util.List;
import java.util.Map;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.GenderData;

public interface GenderDataService {

    public  List<GenderData> getAllGenderData();

    public void createGenderData(Course course, String term);
}
