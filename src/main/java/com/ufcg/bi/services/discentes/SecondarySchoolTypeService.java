package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.discentes.SecondarySchoolType;

public interface SecondarySchoolTypeService {
    public List<SecondarySchoolType> getAllAdmissionTypes();
    public void createAdmissionType(Course course, String term);
}
