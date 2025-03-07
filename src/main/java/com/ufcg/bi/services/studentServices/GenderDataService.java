package com.ufcg.bi.services.studentServices;

import java.util.List;
import java.util.Map;

import com.ufcg.bi.DTO.studentDTOs.GenderDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.GenderData;

public interface GenderDataService {

    public  List<GenderDataDTO> getAllGenderData();

    public void createGenderData(Course course, String term);
}
