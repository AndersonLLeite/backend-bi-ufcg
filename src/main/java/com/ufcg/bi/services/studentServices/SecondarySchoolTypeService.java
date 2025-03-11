package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.SecondarySchoolTypeDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface SecondarySchoolTypeService {
    public List<SecondarySchoolTypeDTO> getAllSecondarySchoolTypes();
    public void createSecondarySchoolType(Course course, String term);
}
