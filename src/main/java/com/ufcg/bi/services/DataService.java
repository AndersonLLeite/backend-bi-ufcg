package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Data;
import com.ufcg.bi.models.FilterData;

import java.util.List;

public interface DataService {
    public Data getData(List<Course> courses, List<String> terms);
    public FilterData getFilterData();
}
