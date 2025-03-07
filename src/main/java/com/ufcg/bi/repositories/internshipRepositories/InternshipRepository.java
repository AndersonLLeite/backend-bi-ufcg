package com.ufcg.bi.repositories.internshipRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.InternshipModels.Internship;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Integer> {
    
}
