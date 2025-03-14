package com.ufcg.bi.repositories.teacherRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.TeacherModels.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    
}
