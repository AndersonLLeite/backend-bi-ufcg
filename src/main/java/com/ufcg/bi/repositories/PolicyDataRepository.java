package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.bi.models.PolicyData;

public interface PolicyDataRepository extends JpaRepository<PolicyData, String> {

    
} 