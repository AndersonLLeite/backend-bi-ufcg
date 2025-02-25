package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.SecondarySchoolType;

public interface SecondarySchoolTypeService {
    public List<SecondarySchoolType> getAllSecondarySchoolTypes();
    public void createSecondarySchoolType(Course course, String term);
}
