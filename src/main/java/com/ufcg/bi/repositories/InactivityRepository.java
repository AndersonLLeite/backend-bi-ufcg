package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.InactivityData;
@Repository
public interface InactivityRepository extends JpaRepository<InactivityData, String> {

    
} 