package com.ufcg.bi.repositories.StudentRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.studentModels.ColorData;
@Repository
public interface ColorDataRepository extends JpaRepository<ColorData, String> {
    
}
