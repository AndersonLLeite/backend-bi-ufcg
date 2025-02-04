package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.discentes.DisabilitiesData;

public interface DisabilitiesDataService {
    public List<DisabilitiesData> getAllDisabilitiesData();
    public void createDisabilitiesData(Course course, String term);
}
