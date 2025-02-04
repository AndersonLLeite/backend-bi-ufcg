package com.ufcg.bi.services.evasao;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.evasao.DropoutByGenderData;

public interface DropoutByGenderDataService {
    public List<DropoutByGenderData> getAllDropoutByGenderData();
    public void createDropoutByGenderData(Course course, String term);
}
