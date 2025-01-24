package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.bi.models.Age;

public interface AgeRepository extends JpaRepository<Age, String>{

    
}