package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.AgeData;

@Repository
public interface AgeDataRepository extends JpaRepository<AgeData, String>{

    
}