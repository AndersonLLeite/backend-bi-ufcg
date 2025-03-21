package com.ufcg.bi.repositories.StudentRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.studentModels.SecondarySchoolType;

@Repository
public interface SecondarySchoolTypeRepository extends JpaRepository<SecondarySchoolType, String> {
    
}
