package com.ufcg.bi.services.internship;

import java.util.List;

import com.ufcg.bi.DTO.InternshipProviderDTO;
import com.ufcg.bi.models.Internship.Internship;

import reactor.core.publisher.Mono;

public interface InternshipService {
    public List<Internship> getAllInternships();
    public void fetchInternships();
    
}
