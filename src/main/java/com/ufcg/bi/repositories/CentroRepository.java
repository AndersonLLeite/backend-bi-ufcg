package com.ufcg.bi.repositories;

import com.ufcg.bi.models.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository extends JpaRepository<Centro, Long> {
}
