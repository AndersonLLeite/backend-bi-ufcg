package com.ufcg.bi.repositories;


import com.ufcg.bi.models.FilterData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterDataRepository extends JpaRepository<FilterData, Long> {
}