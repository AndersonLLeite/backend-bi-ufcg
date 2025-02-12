package com.ufcg.bi.services.evasao;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.evasao.DropoutBySecondarySchoolTypeData;

public interface DropoutBySecondarySchoolTypeDataService {
    public List<DropoutBySecondarySchoolTypeData> getAllDropoutBySecondarySchoolTypeData();
    public void createDropoutBySecondarySchoolTypeData(Course course, String term);

    
}