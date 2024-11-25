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

    // Variável de estado para checkpoint
    private static final Map<String, Boolean> processedCourses = new HashMap<>();

    //@Scheduled(cron = "0 0 0 * * *") // Executar uma vez por dia
    public void synchronizeData() {
        LOGGER.info("Iniciando sincronização de dados...");

        try {
            List<Course> courses = courseService.fetchCourses();
            LOGGER.info("Cursos obtidos: {}", courses.size());

            Set<String> termsSet = new HashSet<>();

            for (Course course : courses) {
                if (processedCourses.getOrDefault(course.getCodigoDoCurso() + "", false)) {
                    LOGGER.info("Curso '{}' já processado, pulando...", course.getDescricao());
                    continue; // Pule cursos já processados
                }

                try {
                    processCourse(course, termsSet);
                    processedCourses.put(course.getCodigoDoCurso() + "", true);
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

    private void processCourse(Course course, Set<String> termsSet) {
        List<Student> students = studentService.fetchStudents(course.getCodigoDoCurso());
        LOGGER.info("Estudantes obtidos para o curso {}: {}", course.getCodigoDoCurso(), students.size());

        // Salvar dados relacionados ao curso
        saveCourseRelatedData(course);

        // Processar estudantes em lotes
        for (int i = 0; i < students.size(); i += 100) {
            List<Student> batch = students.subList(i, Math.min(i + 100, students.size()));
            batch.forEach(student -> {
                student.setCourse(course);
                termsSet.add(student.getPeriodoDeIngresso());
            });

            studentRepository.saveAll(batch); // Persistir lote
        }

        LOGGER.info("Curso '{}' e seus estudantes foram processados.", course.getDescricao());
    }

    private void saveCourseRelatedData(Course course) {
        campusRepository.findById((long) course.getCampus())
                .orElseGet(() -> campusRepository.save(new Campus((long) course.getCampus(), course.getNomeDoCampus())));

        centroRepository.findById((long) course.getCodigoDoSetor())
                .orElseGet(() -> centroRepository.save(new Centro((long) course.getCodigoDoSetor(), course.getNomeDoSetor())));

        cursoRepository.findById((long) course.getCodigoDoCurso())
                .orElseGet(() -> cursoRepository.save(new Curso((long) course.getCodigoDoCurso(), course.getDescricao())));

        courseRepository.save(course);
    }
}
