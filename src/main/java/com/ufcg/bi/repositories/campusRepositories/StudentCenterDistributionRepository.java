package com.ufcg.bi.repositories.campusRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.campusModels.StudentCenterDistribution;
@Repository
public interface StudentCenterDistributionRepository extends JpaRepository<StudentCenterDistribution, String> {
    
}
