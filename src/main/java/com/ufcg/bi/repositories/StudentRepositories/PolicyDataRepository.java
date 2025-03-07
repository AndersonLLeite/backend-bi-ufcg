package com.ufcg.bi.repositories.StudentRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.bi.models.studentModels.PolicyData;

public interface PolicyDataRepository extends JpaRepository<PolicyData, String> {

    
} 