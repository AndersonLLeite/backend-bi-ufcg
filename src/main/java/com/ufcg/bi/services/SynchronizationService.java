package com.ufcg.bi.services;

import com.ufcg.bi.models.*;
import com.ufcg.bi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SynchronizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizationService.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FilterDataRepository filterDataRepository;

    @Autowired
    private CentroRepository centroRepository;

    @Autowired
    private CampusRepository campusRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TermsRepository termsRepository;

    //@Scheduled(cron = "0 * * * * *")
   // @Scheduled(cron = "0 0 0 * * *")
    public void synchronizeData() {
        LOGGER.info("Iniciando sincronização de dados...");

        try {
            List<Course> courses = courseService.fetchCourses();
            LOGGER.info("Cursos obtidos: {}", courses.size());


            Set<String> termsSet = new HashSet<>();

            for (Course course : courses) {
                try {
                    List<Student> students = studentService.fetchStudents(course.getCodigoDoCurso());
                    LOGGER.info("Estudantes obtidos para o curso {}: {}", course.getCodigoDoCurso(), students.size());

                    course.setStudents(students);
                    students.forEach(student -> {
                        student.setCourse(course);
                        if (student.getPeriodoDeIngresso() != null) {
                            termsSet.add(student.getPeriodoDeIngresso());
                        }
                    });

                    // Criar e persistir o Campus
                    Campus campus = new Campus((long) course.getCampus(), course.getNomeDoCampus());
                    // Salvar o campus, caso ainda não esteja no banco
                    campusRepository.save(campus);

                    // Criar e persistir o Centro
                    Centro centro = new Centro((long) course.getCodigoDoSetor(), course.getNomeDoSetor());
                    // Salvar o centro, caso ainda não esteja no banco
                    centroRepository.save(centro);

                    // Criar e persistir o Curso
                    Curso curso = new Curso((long) course.getCodigoDoCurso(), course.getDescricao());
                    // Salvar o curso, caso ainda não esteja no banco
                    cursoRepository.save(curso);

                    courseRepository.save(course); // Salva o curso com os estudantes
                    LOGGER.info("Curso '{}' salvo com sucesso.", course.getDescricao());
                } catch (Exception e) {
                    LOGGER.error("Erro ao processar o curso '{}': {}", course.getDescricao(), e.getMessage());
                }
            }
            List<String> terms = new ArrayList<>(termsSet);
            Terms newTerms = new Terms(terms);
            termsRepository.save(newTerms);
        } catch (Exception e) {
            LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
        }

        LOGGER.info("Sincronização concluída.");
    }

}
