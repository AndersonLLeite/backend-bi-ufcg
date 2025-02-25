package com.ufcg.bi.services.evasao;

import java.util.List;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.evasao.DropoutByAgeData;

public interface DropoutByAgeDataService {
    public List<DropoutByAgeData> getAllDropoutByAgeData();
    public void createDropoutByAgeData(Course course, String term);
}
