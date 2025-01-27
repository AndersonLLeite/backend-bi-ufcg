package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.GenderData;
@Repository
public interface GenderDataRepository extends JpaRepository<GenderData, String>{

    
} 