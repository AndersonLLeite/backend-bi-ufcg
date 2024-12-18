package com.ufcg.bi.controllers;

import com.ufcg.bi.models.*;
import com.ufcg.bi.services.CourseService;
import com.ufcg.bi.services.DataService;
import com.ufcg.bi.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class DataController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DataService dataService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/filter")
    public ResponseEntity<?> getData(@RequestBody FilterData filter) {
        List<Course> courses = courseService.getCoursesByFilter(filter);
        List<String> terms = filter.getTerms();
        Data data = dataService.getData(courses, terms);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/filter_data")
    public ResponseEntity<?> getFilterData() {
        return ResponseEntity.ok(dataService.getFilterData());
    }

    @GetMapping("/cursos")
    public List<Course> getCourses() {
        return courseService.fetchCourses();
    }

    @GetMapping("/students/{courseCode}")
    public List<Student> getStudents(@PathVariable Integer courseCode) {
        return studentService.fetchStudents(courseCode);
    }

    @GetMapping("/all_data")
    public ResponseEntity<?> getAllData() {
        List<Course> courses = courseService.GetCourses();
        List<String> terms = dataService.getFilterData().getTerms();
        return ResponseEntity.ok(dataService.getData(courses, terms));
    }

    
    @GetMapping("/teste")
    public List<DataTeste> getDataTeste() {

        List<DataTeste> dataList = new ArrayList<>();

        // Instância 1: Ciência da Computação
        DataTeste cienciaDaComputacao = new DataTeste(
                1, // codigoDoCurso
                "Ciência da Computação", // descricao
                "Ativo", // status
                10, // codigoDoSetor
                "Centro de Engenharia Elétrica e Informática (CEEI)", // nomeDoSetor
                new ArrayList<>(), // students (vazio para este exemplo)
                "Bacharelado", // grauDoCurso
                1, // campus
                "Campina Grande", // nomeDoCampus
                "Integral", // turno
                "2000.1", // periodoDeInicio
                "01/01/2000", // dataDeFuncionamento
                123456, // codigoInep
                "Presencial", // modalidadeAcademica
                2022, // curriculoAtual
                5, // areaDeRetencao
                2024, // cicloEnade
                "2023.1", // periodo
                60.0, // male (60%)
                40.0, // female (40%)
                20.0, // age_18_20
                50.0, // age_21_25
                30.0, // age_26_plus
                80.0, // statusActive (80%)
                20.0  // statusInactive (20%)
        );
        dataList.add(cienciaDaComputacao);

        // Instância 2: Engenharia Elétrica
        DataTeste engenhariaEletrica = new DataTeste(
                2,
                "Engenharia Elétrica",
                "Ativo",
                11,
                "Centro de Engenharia Elétrica e Informática (CEEI)",
                new ArrayList<>(),
                "Bacharelado",
                1,
                "Campina Grande",
                "Integral",
                "1995.1",
                "01/01/1995",
                654321,
                "Presencial",
                2019,
                6,
                2024,
                "2023.1",
                70.0,
                30.0,
                10.0,
                40.0,
                50.0,
                85.0,
                15.0
        );
        dataList.add(engenhariaEletrica);

        // Instância 3: Medicina
        DataTeste medicina = new DataTeste(
                3,
                "Medicina",
                "Ativo",
                12,
                "Centro de Ciências Biológicas e da Saúde (CCBS)",
                new ArrayList<>(),
                "Bacharelado",
                1,
                "Campina Grande",
                "Integral",
                "2010.1",
                "01/01/2010",
                789123,
                "Presencial",
                2020,
                3,
                2024,
                "2023.1",
                55.0,
                45.0,
                15.0,
                60.0,
                25.0,
                90.0,
                10.0
        );
        dataList.add(medicina);

        // Instância 4: Administração
        DataTeste administracao = new DataTeste(
                4,
                "Administração",
                "Ativo",
                13,
                "Centro de Humanidades (CH)",
                new ArrayList<>(),
                "Bacharelado",
                1,
                "Campina Grande",
                "Noturno",
                "2005.1",
                "01/01/2005",
                456789,
                "Presencial",
                2018,
                8,
                2024,
                "2023.1",
                50.0,
                50.0,
                25.0,
                50.0,
                25.0,
                75.0,
                25.0
        );
        dataList.add(administracao);

        return dataList;
    }

    

    @GetMapping("/courses_json")
public ResponseEntity<?> getCoursesJson() {
    List<Map<String, Object>> response = new ArrayList<>();

    // Curso 1
    Map<String, Object> course1 = new HashMap<>();
    course1.put("codigo_do_curso", 101);
    course1.put("descricao", "Curso de Engenharia de Computação");
    course1.put("status", "Ativo");
    course1.put("codigo_do_setor", 15);
    course1.put("nome_do_setor", "Centro de Engenharia Elétrica e Informática");
    course1.put("grau_do_curso", "Bacharelado");
    course1.put("campus", 1);
    course1.put("nome_do_campus", "Campina Grande");

    List<Map<String, Object>> periods1 = new ArrayList<>();
    Map<String, Object> period1 = new HashMap<>();
    period1.put("periodo", "2020.1");
    period1.put("gender_distribution", Map.of("male", 60.0, "female", 40.0));
    period1.put("age_distribution", Map.of("18-20", 50.0, "21-25", 40.0, "26+", 10.0));
    period1.put("affirmative_policy_distribution", Map.of("yes", 30.0, "no", 70.0));
    periods1.add(period1);

    course1.put("periodos", periods1);
    response.add(course1);

    // Curso 2
    Map<String, Object> course2 = new HashMap<>();
    course2.put("codigo_do_curso", 102);
    course2.put("descricao", "Curso de Matemática");
    course2.put("status", "Inativo");
    course2.put("codigo_do_setor", 16);
    course2.put("nome_do_setor", "Centro de Ciências Exatas e da Natureza");
    course2.put("grau_do_curso", "Licenciatura");
    course2.put("campus", 2);
    course2.put("nome_do_campus", "Patos");

    List<Map<String, Object>> periods2 = new ArrayList<>();
    Map<String, Object> period2 = new HashMap<>();
    period2.put("periodo", "2019.2");
    period2.put("gender_distribution", Map.of("male", 40.0, "female", 60.0));
    period2.put("age_distribution", Map.of("18-20", 30.0, "21-25", 50.0, "26+", 20.0));
    period2.put("affirmative_policy_distribution", Map.of("yes", 40.0, "no", 60.0));
    periods2.add(period2);

    course2.put("periodos", periods2);
    response.add(course2);

    // Curso 3
    Map<String, Object> course3 = new HashMap<>();
    course3.put("codigo_do_curso", 103);
    course3.put("descricao", "Curso de Direito");
    course3.put("status", "Ativo");
    course3.put("codigo_do_setor", 17);
    course3.put("nome_do_setor", "Centro de Ciências Jurídicas");
    course3.put("grau_do_curso", "Bacharelado");
    course3.put("campus", 3);
    course3.put("nome_do_campus", "Sousa");

    List<Map<String, Object>> periods3 = new ArrayList<>();
    Map<String, Object> period3 = new HashMap<>();
    period3.put("periodo", "2021.1");
    period3.put("gender_distribution", Map.of("male", 55.0, "female", 45.0));
    period3.put("age_distribution", Map.of("18-20", 40.0, "21-25", 45.0, "26+", 15.0));
    period3.put("affirmative_policy_distribution", Map.of("yes", 35.0, "no", 65.0));
    periods3.add(period3);

    course3.put("periodos", periods3);
    response.add(course3);

    // Curso 4
    Map<String, Object> course4 = new HashMap<>();
    course4.put("codigo_do_curso", 103);
    course4.put("descricao", "Curso de Direito");
    course4.put("status", "Ativo");
    course4.put("codigo_do_setor", 17);
    course4.put("nome_do_setor", "Centro de Ciências Jurídicas");
    course4.put("grau_do_curso", "Bacharelado");
    course4.put("campus", 3);
    course4.put("nome_do_campus", "Sousa");

    List<Map<String, Object>> periods4 = new ArrayList<>();
    Map<String, Object> period4 = new HashMap<>();
    period4.put("periodo", "2022.1");
    period4.put("gender_distribution", Map.of("male", 50.0, "female", 50.0));
    period4.put("age_distribution", Map.of("18-20", 60.0, "21-25", 30.0, "26+", 10.0));
    period4.put("affirmative_policy_distribution", Map.of("yes", 25.0, "no", 75.0));
    periods4.add(period4);

    course4.put("periodos", periods4);
    response.add(course4);

    // Curso 5
    Map<String, Object> course5 = new HashMap<>();
    course5.put("codigo_do_curso", 102);
    course5.put("descricao", "Curso de Matemática");
    course5.put("status", "Inativo");
    course5.put("codigo_do_setor", 19);
    course5.put("nome_do_setor", "Centro de Ciências Exatas e da Natureza");
    course5.put("grau_do_curso", "Licenciatura");
    course5.put("campus", 2);
    course5.put("nome_do_campus", "Patos");

    List<Map<String, Object>> periods5 = new ArrayList<>();
    Map<String, Object> period5 = new HashMap<>();
    period5.put("periodo", "2023.2");
    period5.put("gender_distribution", Map.of("male", 45.0, "female", 55.0));
    period5.put("age_distribution", Map.of("18-20", 35.0, "21-25", 50.0, "26+", 15.0));
    period5.put("affirmative_policy_distribution", Map.of("yes", 20.0, "no", 80.0));
    periods5.add(period5);

    course5.put("periodos", periods5);
    response.add(course5);

    return ResponseEntity.ok(response);
}

}
