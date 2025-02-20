package com.ufcg.bi.repositories.campus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.campus.DropoutAndEntryCount;
@Repository
public interface DropoutAndEntryCountRepository extends JpaRepository<DropoutAndEntryCount, String> {   
    
}
