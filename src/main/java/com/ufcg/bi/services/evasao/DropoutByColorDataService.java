package com.ufcg.bi.services.evasao;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.evasao.DropoutByColorData;

public interface DropoutByColorDataService {
    public List<DropoutByColorData> getAllDropoutByColorData();
    public void createDropoutByColorData(Course course, String term);
}