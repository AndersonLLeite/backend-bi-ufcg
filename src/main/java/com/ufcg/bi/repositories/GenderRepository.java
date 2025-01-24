package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.bi.models.Gender;

public interface GenderRepository extends JpaRepository<Gender, String>{

    
} 