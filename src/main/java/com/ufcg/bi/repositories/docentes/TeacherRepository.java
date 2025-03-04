package com.ufcg.bi.repositories.docentes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.docentes.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    
}
