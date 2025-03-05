package com.ufcg.bi.services.subjectService;

import java.util.List;

import com.ufcg.bi.models.subject.Subject;

public interface SubjectService {
    public List<Subject> getAllSubjects();
    public void fetchSubjects();
    
}
