package com.ufcg.bi.repositories.dropoutRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.dropoutModels.DropoutByAgeData;

@Repository
public interface DropoutByAgeDataRepository extends JpaRepository<DropoutByAgeData, String> {

    
}