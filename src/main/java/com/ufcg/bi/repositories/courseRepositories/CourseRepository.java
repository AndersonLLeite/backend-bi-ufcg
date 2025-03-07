package com.ufcg.bi.repositories.courseRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.courseModels.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    
}
