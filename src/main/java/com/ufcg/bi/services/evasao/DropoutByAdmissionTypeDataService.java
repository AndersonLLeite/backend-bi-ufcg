 package com.ufcg.bi.services.evasao;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.evasao.DropoutByAdmissionTypeData;

public interface DropoutByAdmissionTypeDataService {
        public List<DropoutByAdmissionTypeData> getAllDropoutByAdmissionTypeData();
        public void createDropoutByAdmissionTypeData(Course course, String term);

    
}