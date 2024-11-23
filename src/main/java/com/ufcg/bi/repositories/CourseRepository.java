package com.ufcg.bi.repositories;

import com.ufcg.bi.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCodigoDoCursoIn(List<Integer> courseCodes);
}
