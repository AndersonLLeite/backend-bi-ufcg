 package com.ufcg.bi.services.dropoutServices;

import java.util.List;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutByAdmissionTypeDataDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface DropoutByAdmissionTypeDataService {
        public List<DropoutByAdmissionTypeDataDTO> getAllDropoutByAdmissionTypeData();
        public void createDropoutByAdmissionTypeData(Course course, String term);

    
}