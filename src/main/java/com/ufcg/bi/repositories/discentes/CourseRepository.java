package com.ufcg.bi.repositories.discentes;

import com.ufcg.bi.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("""
        SELECT c FROM Course c 
        WHERE 
            (:centros IS NULL OR c.codigoDoSetor IN :centros) AND
            (:campus IS NULL OR c.campus IN :campus) AND
            (:cursos IS NULL OR c.codigoDoCurso IN :cursos)
    """)
    List<Course> findCoursesByFilters(
            @Param("centros") List<Integer> centros,
            @Param("campus") List<Integer> campus,
            @Param("cursos") List<Integer> cursos
    );

    List<Course> findByCodigoDoCursoIn(List<Integer> courseCodes);
}
