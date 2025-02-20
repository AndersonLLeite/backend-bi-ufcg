package com.ufcg.bi.repositories.campus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.campus.StudentStatusDistribution;

@Repository
public interface StudentStatusDistributionRepository extends JpaRepository<StudentStatusDistribution, String> {
    
}
