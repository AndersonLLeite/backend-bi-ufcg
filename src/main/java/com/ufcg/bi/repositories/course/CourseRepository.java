package com.ufcg.bi.repositories.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.course.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    
}
