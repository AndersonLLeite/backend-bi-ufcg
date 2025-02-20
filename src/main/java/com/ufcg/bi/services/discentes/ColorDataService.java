package com.ufcg.bi.services.discentes;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.discentes.ColorData;

public interface ColorDataService {
 public List<ColorData> getAllColorData();
 public void createColorData(Course course, String term);
}