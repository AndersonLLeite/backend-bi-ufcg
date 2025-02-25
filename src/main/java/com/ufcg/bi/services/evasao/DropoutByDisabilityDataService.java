package com.ufcg.bi.services.evasao;

import java.util.List;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.evasao.DropoutByDisabilityData;

public interface DropoutByDisabilityDataService {
    public List<DropoutByDisabilityData> getAllDropoutByDisabilityData();
    public void createDropoutByDisabilityData(Course course, String term);
    
    
}
