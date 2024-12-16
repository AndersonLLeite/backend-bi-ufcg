package com.ufcg.bi.repositories;

import com.ufcg.bi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // Aqui você pode adicionar métodos personalizados de consulta se necessário
    List<Student> findByCourse_CodigoDoCurso(int codigoDoCurso);
    List<Student> findByMatriculaDoEstudante(String matriculaDoEstudante);
    List<Student> findByPoliticaAfirmativa(String politicaAfirmativa);
    // Métodos personalizados para consultar por outros campos, como estado civil, situação, etc.
    List<Student> findBySituacao(String situacao);
}
