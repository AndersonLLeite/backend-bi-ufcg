package com.ufcg.bi.repositories.campusRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.campusModels.StudentCount;
@Repository
public interface StudentCountRepository extends JpaRepository<StudentCount, String> {
    
}
