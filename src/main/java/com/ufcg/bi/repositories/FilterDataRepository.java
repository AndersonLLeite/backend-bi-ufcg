package com.ufcg.bi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.FilterData;

@Repository
public interface FilterDataRepository extends JpaRepository<FilterData, String>  {

    
}
