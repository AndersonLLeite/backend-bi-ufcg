package com.ufcg.bi.repositories.internship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.Internship.Internship;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Integer> {
    
}
