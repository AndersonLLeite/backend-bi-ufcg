package com.ufcg.bi.repositories.StudentRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.filterModels.FilterData;

@Repository
public interface FilterDataRepository extends JpaRepository<FilterData, String>  {

    
}
