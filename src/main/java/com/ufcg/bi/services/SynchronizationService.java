package com.ufcg.bi.services;

import com.ufcg.bi.models.*;
import com.ufcg.bi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

import java.util.*;

@Service
public class SynchronizationService {

    // private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizationService.class);

    // @Autowired
    // private CourseService courseService;

    // @Autowired
    // private StudentService studentService;

    // @Autowired
    // private CourseRepository courseRepository;

    // @Autowired
    // private StudentRepository studentRepository;

    // @Autowired
    // private FilterDataRepository filterDataRepository;

    // @Autowired
    // private CentroRepository centroRepository;

    // @Autowired
    // private CampusRepository campusRepository;

    // @Autowired
    // private CursoRepository cursoRepository;

    // @Autowired
    // private TermsRepository termsRepository;

    // // Variável de estado para checkpoint
    // private static final Map<String, Boolean> processedCourses = new HashMap<>();

    // //@Scheduled(cron = "0 0 0 * * *") // Executar uma vez por dia

    // public void synchronizeData() {
    //     LOGGER.info("Iniciando sincronização de dados...");
    
    //     ExecutorService executorService = Executors.newFixedThreadPool(4); // Define um pool com 4 threads
    //     List<Future<?>> futures = new ArrayList<>();
    
    //     try {
    //         List<Course> courses = courseService.fetchCourses();
    //         LOGGER.info("Cursos obtidos: {}", courses.size());
    
    //         Set<String> termsSet = ConcurrentHashMap.newKeySet(); // Set thread-safe para coletar períodos
    
    //         int limit = Math.min(10, courses.size()); // Limita a 5 cursos para teste
    //         for (int i = 0; i < limit; i++) {
    //             Course course = courses.get(i);
    
    //             if (processedCourses.getOrDefault(course.getCodigoDoCurso() + "", false)) {
    //                 LOGGER.info("Curso '{}' já processado, pulando...", course.getDescricao());
    //                 continue; // Pule cursos já processados
    //             }
    
    //             // Submete cada curso para processamento em uma thread separada
    //             Future<?> future = executorService.submit(() -> {
    //                 try {
    //                     processCourse(course, termsSet);
    //                     processedCourses.put(course.getCodigoDoCurso() + "", true);
    //                 } catch (Exception e) {
    //                     LOGGER.error("Erro ao processar o curso '{}': {}", course.getDescricao(), e.getMessage());
    //                 }
    //             });
    //             futures.add(future);
    //         }
    
    //         // Aguarda a conclusão de todas as threads
    //         for (Future<?> future : futures) {
    //             try {
    //                 future.get(); // Bloqueia até que a tarefa termine ou lance uma exceção
    //             } catch (ExecutionException | InterruptedException e) {
    //                 LOGGER.error("Erro durante a execução de uma thread: {}", e.getMessage());
    //             }
    //         }
    
    //         termsRepository.save(new Terms(new ArrayList<>(termsSet)));
    //     } catch (Exception e) {
    //         LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
    //     } finally {
    //         executorService.shutdown(); // Finaliza o pool de threads
    //         LOGGER.info("Sincronização concluída.");
    //     }
    // }
    


    // private void processCourse(Course course, Set<String> termsSet) {
    //     List<Student> students = studentService.fetchStudents(course.getCodigoDoCurso());
    //     LOGGER.info("Estudantes obtidos para o curso {}: {}", course.getCodigoDoCurso(), students.size());

    //     // Salvar dados relacionados ao curso
    //     saveCourseRelatedData(course);

    //     // Processar estudantes em lotes
    //     for (int i = 0; i < students.size(); i += 100) {
    //         List<Student> batch = students.subList(i, Math.min(i + 100, students.size()));
    //         batch.forEach(student -> {
    //             student.setCourse(course);
    //             termsSet.add(student.getPeriodoDeIngresso());
    //         });

    //         studentRepository.saveAll(batch); // Persistir lote
    //     }

    //     LOGGER.info("Curso '{}' e seus estudantes foram processados.", course.getDescricao());
    // }

    // private void saveCourseRelatedData(Course course) {
    //     campusRepository.findById((long) course.getCampus())
    //             .orElseGet(() -> campusRepository.save(new Campus((long) course.getCampus(), course.getNomeDoCampus())));

    //     centroRepository.findById((long) course.getCodigoDoSetor())
    //             .orElseGet(() -> centroRepository.save(new Centro((long) course.getCodigoDoSetor(), course.getNomeDoSetor())));

    //     cursoRepository.findById((long) course.getCodigoDoCurso())
    //             .orElseGet(() -> cursoRepository.save(new Curso((long) course.getCodigoDoCurso(), course.getDescricao())));

    //     courseRepository.save(course);
    // }


    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizationService.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

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

    @Autowired
    private DataService2 dataService2;

    @Autowired
    private DataRepository dataRepository;


    //@Scheduled(cron = "0 0 0 * * *") // Executar uma vez por dia

    public void synchronizeData() {
        LOGGER.info("Iniciando sincronização de dados...");

        try {
            List<Course> courses = courseService.fetchCourses();
            LOGGER.info("Cursos obtidos: {}", courses.size());

            int limit = Math.min(5, courses.size()); // Limita a 10 cursos para teste
            for (int i = 0; i < limit; i++) {
                Course course = courses.get(i);
                try {
                    Course courseProcessed = processCourse(course);
                    List<String> terms = courseProcessed.getPeriodos();
                    for (String term : terms) {
                        Data2 data = dataService2.createData(courseProcessed, term);
                        dataRepository.save(data);
                    }
                   
                } catch (Exception e) {
                    LOGGER.error("Erro ao processar o curso '{}': {}", course.getDescricao(), e.getMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Erro geral durante a sincronização: {}", e.getMessage());
        }

        LOGGER.info("Sincronização concluída.");
    }

    private Course processCourse(Course course) {
        List<Student> students = studentService.fetchStudents(course.getCodigoDoCurso());
        LOGGER.info("Estudantes obtidos para o curso {}: {}", course.getCodigoDoCurso(), students.size());

        // Associar estudantes ao curso localmente
        course.setStudents(students);
        Set<String> termsSet = new HashSet<>();
        // Processar estudantes em lotes
        for (int i = 0; i < students.size(); i += 100) {
            List<Student> batch = students.subList(i, Math.min(i + 100, students.size()));
            batch.forEach(student -> {
                student.setCourse(course);
                termsSet.add(student.getPeriodoDeIngresso());
            });
        }
        course.setPeriodos(new ArrayList<>(termsSet));
        LOGGER.info("Curso '{}' e seus estudantes foram processados.", course.getDescricao());

        return course;

    }


}
