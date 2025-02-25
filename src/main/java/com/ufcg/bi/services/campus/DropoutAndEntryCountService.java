package com.ufcg.bi.services.campus;

import java.util.List;

import com.ufcg.bi.models.campus.DropoutAndEntryCount;
import com.ufcg.bi.models.course.Course;

public interface DropoutAndEntryCountService {
    public List<DropoutAndEntryCount> getAllDropoutAndEntryCount();
    public void createDropoutAndEntryCount( Course course, String term);
  
    
}
