package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.DTO.studentDTOs.ColorDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.ColorData;

public interface ColorDataService {
 public List<ColorDataDTO> getAllColorData();
 public void createColorData(Course course, String term);
}