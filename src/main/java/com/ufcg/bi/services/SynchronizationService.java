package com.ufcg.bi.services;

import com.ufcg.bi.models.*;
import com.ufcg.bi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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

                    // Processar em lotes
                    for (int i = 0; i < students.size(); i += 100) {
                        List<Student> batch = students.subList(i, Math.min(i + 100, students.size()));
                        batch.forEach(student -> {
                            student.setCourse(course);
                            termsSet.add(student.getPeriodoDeIngresso());
                        });

                        // Persistir o lote
                        studentRepository.saveAll(batch);
                    }

                    // Salvar o Campus, caso ainda não esteja no banco
                    Optional<Campus> existingCampus = campusRepository.findById((long) course.getCampus());
                    if (existingCampus.isEmpty()) {
                        campusRepository.save(new Campus((long) course.getCampus(), course.getNomeDoCampus()));
                    }

// Salvar o Centro, caso ainda não esteja no banco
                    Optional<Centro> existingCentro = centroRepository.findById((long) course.getCodigoDoSetor());
                    if (existingCentro.isEmpty()) {
                        centroRepository.save(new Centro((long) course.getCodigoDoSetor(), course.getNomeDoSetor()));
                    }

// Salvar o Curso, caso ainda não esteja no banco
                    Optional<Curso> existingCurso = cursoRepository.findById((long) course.getCodigoDoCurso());
                    if (existingCurso.isEmpty()) {
                        cursoRepository.save(new Curso((long) course.getCodigoDoCurso(), course.getDescricao()));
                    }
                    courseRepository.save(course); // Salva sem associar todos os estudantes
                    LOGGER.info("Curso '{}' salvo com sucesso.", course.getDescricao());
                } catch (Exception e) {
                    LOGGER.error("Erro ao processar o curso '{}': {}", course.getDescricao(), e.getMessage());
                }
            }

            termsRepository.save(new Terms(new ArrayList<>(termsSet)));
        } catch (Exception e) {
            LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
        }

        LOGGER.info("Sincronização concluída.");
    }


}
