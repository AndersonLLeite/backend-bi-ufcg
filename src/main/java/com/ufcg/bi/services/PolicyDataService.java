package com.ufcg.bi.services;

import java.util.List;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.PolicyData;

public interface PolicyDataService {
    public List<PolicyData> getAllPolicyData();
    public void createPolicyData(Course course, String term);
}
