package com.ufcg.bi.services.subjectServices;

import java.util.List;

import com.ufcg.bi.DTO.subjectDTOs.SubjectDTO;

public interface SubjectService {
    public List<SubjectDTO> getAllSubjects();
    public void fetchSubjects();
    
}
