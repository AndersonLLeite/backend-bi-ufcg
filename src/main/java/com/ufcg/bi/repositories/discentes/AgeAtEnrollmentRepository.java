package com.ufcg.bi.repositories.discentes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.discentes.AgeAtEnrollment;

@Repository
public interface AgeAtEnrollmentRepository extends JpaRepository<AgeAtEnrollment, String>{

    
} 
