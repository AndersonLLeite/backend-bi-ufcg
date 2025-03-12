package com.ufcg.bi.services.internshipServices;

import java.util.List;

import com.ufcg.bi.DTO.internshipDTOs.InternshipDTO;


public interface InternshipService {
    public List<InternshipDTO> getAllInternships();
    public void fetchInternships();
    
}
