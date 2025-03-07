package com.ufcg.bi.services.internshipServices;

import java.util.List;

import com.ufcg.bi.DTO.internshipDTOs.InternshipDTO;
import com.ufcg.bi.models.InternshipModels.Internship;

import reactor.core.publisher.Mono;

public interface InternshipService {
    public List<InternshipDTO> getAllInternships();
    public void fetchInternships();
    
}
