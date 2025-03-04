package com.ufcg.bi.repositories.subject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.bi.models.subject.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findAllByCodigoDaDisciplinaIn(List<Integer> ids);
    
}
