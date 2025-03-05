package com.ufcg.bi.services.course;

import com.ufcg.bi.DTO.CourseDTO;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.EntrantGeolocation;
import com.ufcg.bi.repositories.course.CourseRepository;
import com.ufcg.bi.services.campus.DropoutAndEntryCountService;
import com.ufcg.bi.services.campus.StudentCenterDistributionService;
import com.ufcg.bi.services.campus.StudentCountService;
import com.ufcg.bi.services.campus.StudentStatusDistributionService;
import com.ufcg.bi.services.discentes.AdmissionTypeService;
import com.ufcg.bi.services.discentes.AgeAtEnrollmentService;
import com.ufcg.bi.services.discentes.ColorDataService;
import com.ufcg.bi.services.discentes.DisabilitiesDataService;
import com.ufcg.bi.services.discentes.EntrantGeolocationService;
import com.ufcg.bi.services.discentes.GenderDataService;
import com.ufcg.bi.services.discentes.InactivityDataService;
import com.ufcg.bi.services.discentes.PolicyDataService;
import com.ufcg.bi.services.discentes.SecondarySchoolTypeService;
import com.ufcg.bi.services.discentes.StudentService;
import com.ufcg.bi.services.evasao.DropoutByAdmissionTypeDataService;
import com.ufcg.bi.services.evasao.DropoutByAgeDataService;
import com.ufcg.bi.services.evasao.DropoutByColorDataService;
import com.ufcg.bi.services.evasao.DropoutByDisabilityDataService;
import com.ufcg.bi.services.evasao.DropoutByGenderDataService;
import com.ufcg.bi.services.evasao.DropoutBySecondarySchoolTypeDataService;
import com.ufcg.bi.services.evasao.DropoutGeolocationService;
import com.ufcg.bi.services.filter.FilterDataService;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    @Value("${app.service.base-url}")
    private String baseUrl;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FilterDataService filterDataService;

    @Autowired
    private GenderDataService genderDataService;

    @Autowired
    private PolicyDataService policyDataService;

    @Autowired
    private InactivityDataService inactivityDataService;

    @Autowired
    private AgeAtEnrollmentService ageAtEnrollmentService;

    @Autowired
    private ColorDataService colorDataService;

    @Autowired
    private DisabilitiesDataService disabilitiesDataService;

    @Autowired
    private DropoutByColorDataService dropoutByColorDataService;

    @Autowired
    private DropoutByGenderDataService dropoutByGenderDataService;

    @Autowired
    private DropoutByAgeDataService dropoutByAgeDataService;

    @Autowired
    private DropoutByDisabilityDataService dropoutByDisabilityDataService;

    @Autowired
    private DropoutByAdmissionTypeDataService dropoutByAdmissionTypeDataService;

    @Autowired
    private DropoutBySecondarySchoolTypeDataService dropoutBySecondarySchoolTypeDataService;

    @Autowired
    private StudentCenterDistributionService studentCenterDistributionService;

    @Autowired
    private StudentStatusDistributionService studentStatusDistributionService;

    @Autowired
    private StudentCountService studentCountService;

    @Autowired
    private DropoutAndEntryCountService dropoutAndEntryCountService;

    @Autowired
    private SecondarySchoolTypeService secondarySchoolTypeService;

    @Autowired
    private AdmissionTypeService admissionTypeService;

    @Autowired
    private EntrantGeolocationService entrantGeolocationService;

    @Autowired
    private DropoutGeolocationService dropoutGeolocationService;

    private WebClient webClient;

    @Autowired
    private void initWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        LOGGER.info("Convertendo cursos para DTOs.");
        return courseRepository.findAll()
                .stream()
                .map(CourseDTO::new)
                .toList();
    }

    @Transactional
    @Retryable(
        value = { WebClientException.class }, 
        maxAttempts = 3, 
        backoff = @Backoff(delay = 5000)
    )
    @Override
    public void fetchCourses() {
        int page = 1;
        int pageSize = 200;
        List<Course> allCourses = new ArrayList<>();

        LOGGER.info("Iniciando busca paginada de cursos.");

        while (true) {
            List<Course> courses = fetchCoursesFromPage(page, pageSize);
            if (courses == null || courses.isEmpty()) {
                LOGGER.info("Nenhum curso encontrado na página {}. Encerrando busca.", page);
                break;
            }

            LOGGER.info("{} cursos encontrados na página {}.", courses.size(), page);

            // Para cada curso obtido, processa os dados (estudantes, períodos, etc.)
            for (Course course : courses) {
                processCourse(course);
            }
            allCourses.addAll(courses);

            if (allCourses.size() >= 500) {
                saveCoursesBatch(allCourses);
                allCourses.clear();
            }
            page++;
        }

        if (!allCourses.isEmpty()) {
            saveCoursesBatch(allCourses);
        }

    }

    private List<Course> fetchCoursesFromPage(int page, int pageSize) {
        try {
            Mono<List<Course>> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/cursos")
                            .queryParam("pagina", page)
                            .queryParam("tamanho", pageSize)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Course>>() {});
            return response.block();
        } catch (WebClientException e) {
            LOGGER.error("Erro ao buscar cursos na página {}: {}", page, e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveCoursesBatch(List<Course> courses) {
        try {
            courseRepository.saveAll(courses);
            LOGGER.info("Foram salvos {} cursos no banco de dados.", courses.size());
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar cursos: {}", e.getMessage());
        }
    }

    private void processCourse(Course course) {
        try {
            List<Student> students = studentService.fetchStudents(course.getCodigoDoCurso());
            LOGGER.info("Estudantes obtidos para o curso {}: {}", course.getCodigoDoCurso(), students.size());

            course.setStudents(students);
            Set<String> termsSet = new HashSet<>();
            for (Student student : students) {
                student.setCourse(course);
                termsSet.add(student.getPeriodoDeIngresso());
            }
            course.setPeriodos(new ArrayList<>(termsSet));
            LOGGER.info("Curso '{}' e seus estudantes foram processados.", course.getDescricao());

            processCourseData(course);
        } catch (Exception e) {
            LOGGER.error("Erro ao processar o curso '{}': {}", course.getDescricao(), e.getMessage());
        }
    }

    private void processCourseData(Course course) {
        for (String term : course.getPeriodos()) {
            filterDataService.createFilterData(course, term);
            genderDataService.createGenderData(course, term);
            policyDataService.createPolicyData(course, term);
            inactivityDataService.createInactivityData(course, term);
            ageAtEnrollmentService.createAgeAtEnrollment(course, term);
            colorDataService.createColorData(course, term);
            disabilitiesDataService.createDisabilitiesData(course, term);
            secondarySchoolTypeService.createSecondarySchoolType(course, term);
            dropoutByColorDataService.createDropoutByColorData(course, term);
            dropoutByGenderDataService.createDropoutByGenderData(course, term);
            dropoutByAgeDataService.createDropoutByAgeData(course, term);
            dropoutByDisabilityDataService.createDropoutByDisabilityData(course, term);
            dropoutByAdmissionTypeDataService.createDropoutByAdmissionTypeData(course, term);
            dropoutBySecondarySchoolTypeDataService.createDropoutBySecondarySchoolTypeData(course, term);
            studentCenterDistributionService.createStudentCenterDistribution(course, term);
            studentStatusDistributionService.createStudentStatusDistribution(course, term);
            studentCountService.createStudentCount(course, term);
            dropoutAndEntryCountService.createDropoutAndEntryCount(course, term);
            admissionTypeService.createAdmissionType(course, term);
            entrantGeolocationService.createEntrantGeolocation(course, term);
            dropoutGeolocationService.createDropoutGeolocation(course, term);
        }
    }
}
