package com.ufcg.bi.repositories.dropoutRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.dropoutModels.DropoutGeolocation;

@Repository
public interface DropoutGeolocationRepository extends JpaRepository<DropoutGeolocation, String> {
    
}
