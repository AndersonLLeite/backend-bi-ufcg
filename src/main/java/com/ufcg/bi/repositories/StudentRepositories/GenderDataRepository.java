package com.ufcg.bi.repositories.StudentRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.studentModels.GenderData;
@Repository
public interface GenderDataRepository extends JpaRepository<GenderData, String>{

    
} 