package com.ufcg.bi.repositories.evasao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.evasao.DropoutBySecondarySchoolTypeData;
@Repository
public interface DropoutBySecondarySchoolTypeDataRepository extends JpaRepository<DropoutBySecondarySchoolTypeData, String> {
    
}
