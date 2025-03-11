package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.GenderDataDTO;
import com.ufcg.bi.models.courseModels.Course;

public interface GenderDataService {

    public  List<GenderDataDTO> getAllGenderData();

    public void createGenderData(Course course, String term);
}
