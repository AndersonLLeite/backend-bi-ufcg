package com.ufcg.bi.services.course;

import com.ufcg.bi.DTO.CourseDTO;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.repositories.course.CourseRepository;
import com.ufcg.bi.services.FilterDataService;
import com.ufcg.bi.services.StudentService;
import com.ufcg.bi.services.campus.DropoutAndEntryCountService;
import com.ufcg.bi.services.campus.StudentCenterDistributionService;
import com.ufcg.bi.services.campus.StudentCountService;
import com.ufcg.bi.services.campus.StudentStatusDistributionService;
import com.ufcg.bi.services.discentes.AgeAtEnrollmentService;
import com.ufcg.bi.services.discentes.ColorDataService;
import com.ufcg.bi.services.discentes.DisabilitiesDataService;
import com.ufcg.bi.services.discentes.GenderDataService;
import com.ufcg.bi.services.discentes.InactivityDataService;
import com.ufcg.bi.services.discentes.PolicyDataService;
import com.ufcg.bi.services.discentes.SecondarySchoolTypeService;
import com.ufcg.bi.services.evasao.DropoutByAdmissionTypeDataService;
import com.ufcg.bi.services.evasao.DropoutByAgeDataService;
import com.ufcg.bi.services.evasao.DropoutByColorDataService;
import com.ufcg.bi.services.evasao.DropoutByDisabilityDataService;
import com.ufcg.bi.services.evasao.DropoutByGenderDataService;
import com.ufcg.bi.services.evasao.DropoutBySecondarySchoolTypeDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final WebClient webClient;

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
    public CourseServiceImpl(
            @Value("${app.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }


    @Override
    public List<Course> fetchCourses() {
        Mono<List<Course>> response = webClient.get()
                .uri("/cursos")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Course>>() {});
        List<Course> courses = response.block();
        
        if (courses != null && !courses.isEmpty()) {
            courseRepository.saveAll(courses);
            LOGGER.info("Todos os cursos foram salvos no banco de dados.");
        } else {
            LOGGER.warn("Nenhum curso encontrado para salvar no banco de dados.");
        }
        
        return courses;
    }

    @Override
    public void processCourse(Course course) {
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
        List<String> terms = course.getPeriodos();
        for (String term : terms) {
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
            
        }
    }


    @Override
public List<CourseDTO> getAllCourses() {
    return courseRepository.findAll()
            .stream()
            .map(CourseDTO::new)
            .toList();
}
}
