package com.ufcg.bi.services.discentes;

import java.util.List;
import java.util.Map;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.GenderData;

public interface GenderDataService {

    public  List<GenderData> getAllGenderData();

    public void createGenderData(Course course, String term);
}
