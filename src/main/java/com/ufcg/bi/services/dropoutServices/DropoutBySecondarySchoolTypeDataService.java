package com.ufcg.bi.services.dropoutServices;

import java.util.List;

import com.ufcg.bi.DTO.dropoutDTOs.DropoutBySecondarySchoolTypeDataDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface DropoutBySecondarySchoolTypeDataService {
    public List<DropoutBySecondarySchoolTypeDataDTO> getAllDropoutBySecondarySchoolTypeData();
    public void createDropoutBySecondarySchoolTypeData(Course course, String term);

    
}