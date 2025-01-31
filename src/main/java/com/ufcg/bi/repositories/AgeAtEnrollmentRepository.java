package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.AgeAtEnrollment;

@Repository
public interface AgeAtEnrollmentRepository extends JpaRepository<AgeAtEnrollment, String>{

    
} 
