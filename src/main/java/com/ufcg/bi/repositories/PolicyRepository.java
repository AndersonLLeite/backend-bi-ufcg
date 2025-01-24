package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.bi.models.Policy;

public interface PolicyRepository extends JpaRepository<Policy, String> {

    
} 