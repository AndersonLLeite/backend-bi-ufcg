package com.ufcg.bi.services;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Data;
import com.ufcg.bi.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
@Service
public class DataServiceImpl implements DataService {

    @Override
    public Data getData(List<Course> courses, List<String> terms) {
        Data data = new Data();
        data.setGenderDistribution(getGenderDistribution(courses, terms));
        data.setAgeDistribution(getAgeDistribution(courses, terms));
        data.setAffirmativePolicyDistribution(getAffirmativePolicyDistribution(courses, terms));
        data.setStatusDistribution(getStatusDistribution(courses, terms));
        data.setInactivityReasonsDistribution(getInactivityReasonDistribution(courses, terms));
        data.setInactivityPerPeriodoDeEvasaoDistribution(getInactivityPerPeriodoDeEvasaoDistribution(courses, terms));
        data.setAdmissionTypeDistribution(getAdmissionTypeDistribution(courses, terms));
        data.setSecondarySchoolTypeDistribution(getSecondarySchoolTypeDistribution(courses, terms));
        data.setOriginDistribution(getOriginDistribution(courses, terms));
        data.setColorDistribution(getColorDistribution(courses, terms));
        data.setDisabilitiesDistribution(getDisabilitiesDistribution(courses, terms));
        data.setAgeAtEnrollmentDistribution(getAgeAtEnrollmentDistribution(courses, terms));
        data.setCreditCompletedVsFailedDistribution(getCreditCompletedVsFailedDistribution(courses, terms));
        data.setEvasionStatisticsByEvasionPeriod(getEvasionStatisticsByEvasionPeriod(courses, terms));
        data.setGraduationStatisticsByEvasionPeriod(getGraduationStatisticsByEvasionPeriod(courses, terms));
        data.setEvasionByColor(getEvasionByColor(courses, terms));
        data.setEvasionByAge(getEvasionByAge(courses, terms));
        data.setEvasionByGender(getEvasionByGender(courses, terms));
        data.setEvasionByAdmissionType(getEvasionByAdmissionType(courses, terms));
        data.setEvasionBySecondarySchoolType(getEvasionBySecondarySchoolType(courses, terms));
        data.setEvasionByDisabilities(getEvasionByDisabilities(courses, terms));
        return data;
    }

    private String getAgeRange(String age) {
        int idade = Integer.parseInt(age);

        if (idade >= 16 && idade <= 18) {
            return "16-18";
        } else if (idade >= 19 && idade <= 21) {
            return "19-21";
        } else if (idade >= 22 && idade <= 24) {
            return "22-24";
        } else if (idade >= 25 && idade <= 27) {
            return "25-27";
        } else if (idade >= 28 && idade <= 30) {
            return "28-30";
        } else {
            return "31+";
        }
    }


    private Map<String, Map<String, Double>> getGenderDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> genderDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }
                genderDistribution.putIfAbsent(student.getPeriodoDeIngresso(), new HashMap<>());
                Map<String, Double> genderMap = genderDistribution.get(student.getPeriodoDeIngresso());
                genderMap.putIfAbsent(student.getGenero(), 0.0);
                genderMap.put(student.getGenero(), genderMap.get(student.getGenero()) + 1);
            }
        }
        return genderDistribution;
    }

    private Map<String, Map<String, Double>> getAgeDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> ageDistribution = new HashMap<>();
        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }
                String ageRange = getAgeRange(student.getIdade());
                if (!ageDistribution.containsKey(student.getPeriodoDeIngresso())) {
                    ageDistribution.put(student.getPeriodoDeIngresso(), new HashMap<>());
                }
                if (!ageDistribution.get(student.getPeriodoDeIngresso()).containsKey(ageRange)) {
                    ageDistribution.get(student.getPeriodoDeIngresso()).put(ageRange, 0.0);
                }
                ageDistribution.get(student.getPeriodoDeIngresso())
                        .put(ageRange, ageDistribution.get(student.getPeriodoDeIngresso()).get(ageRange) + 1);
            }
        }
        return ageDistribution;
    }

    private Map<String, Map<String, Double>> getAffirmativePolicyDistribution(
            List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> affirmativePolicyDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null ||
                        !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }
                if (!affirmativePolicyDistribution.containsKey(student.getPeriodoDeIngresso())) {
                    affirmativePolicyDistribution.put(student.getPeriodoDeIngresso(), new HashMap<>());
                }
                Map<String, Double> policies = affirmativePolicyDistribution.get(student.getPeriodoDeIngresso());
                if (!policies.containsKey(student.getPoliticaAfirmativa())) {
                    policies.put(student.getPoliticaAfirmativa(), 0.0);
                }
                policies.put(student.getPoliticaAfirmativa(), policies.get(student.getPoliticaAfirmativa()) + 1);
            }
        }
        return affirmativePolicyDistribution;
    }
    private Map<String, Map<String, Double>> getStatusDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> statusDistribution = new HashMap<>();
        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }
                statusDistribution.putIfAbsent(student.getPeriodoDeIngresso(), new HashMap<>());
                Map<String, Double> situacaoMap = statusDistribution.get(student.getPeriodoDeIngresso());
                situacaoMap.putIfAbsent(student.getSituacao(), 0.0);
                situacaoMap.put(student.getSituacao(), situacaoMap.get(student.getSituacao()) + 1);
            }
        }
        return statusDistribution;
    }

    private Map<String, Map<String, Double>> getInactivityReasonDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> inactivityReasonDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null ||
                        student.getPeriodoDeEvasao() == null ||
                        !student.getPeriodoDeIngresso().equals(student.getPeriodoDeEvasao()) ||
                        !terms.contains(student.getPeriodoDeIngresso()) ||
                        student.getMotivoDeEvasao() == null) {
                    continue;
                }

                if (!inactivityReasonDistribution.containsKey(student.getPeriodoDeIngresso())) {
                    inactivityReasonDistribution.put(student.getPeriodoDeIngresso(), new HashMap<>());
                }

                if (!inactivityReasonDistribution.get(student.getPeriodoDeIngresso()).containsKey(student.getMotivoDeEvasao())) {
                    inactivityReasonDistribution.get(student.getPeriodoDeIngresso()).put(student.getMotivoDeEvasao(), 0.0);
                }

                inactivityReasonDistribution.get(student.getPeriodoDeIngresso()).put(student.getMotivoDeEvasao(),
                        inactivityReasonDistribution.get(student.getPeriodoDeIngresso()).get(student.getMotivoDeEvasao()) + 1);
            }
        }

        return inactivityReasonDistribution;
    }

    public Map<String, Map<String, Double>> getInactivityPerPeriodoDeEvasaoDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> inactivityDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        student.getMotivoDeEvasao() == null ||
                        student.getMotivoDeEvasao().equals("REGULAR") ||
                        student.getMotivoDeEvasao().equals("GRADUADO")) {
                    continue;
                }

                inactivityDistribution
                        .computeIfAbsent(student.getPeriodoDeEvasao(), k -> new HashMap<>())
                        .computeIfAbsent(student.getMotivoDeEvasao(), k -> 0.0);

                inactivityDistribution.get(student.getPeriodoDeEvasao())
                        .put(student.getMotivoDeEvasao(), inactivityDistribution.get(student.getPeriodoDeEvasao()).get(student.getMotivoDeEvasao()) + 1);
            }
        }

        return inactivityDistribution;
    }

    private Map<String, Map<String, Double>> getAdmissionTypeDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> admissionTypeDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }
                admissionTypeDistribution
                        .computeIfAbsent(student.getPeriodoDeIngresso(), k -> new HashMap<>())
                        .merge(student.getFormaDeIngresso(), 1.0, Double::sum);
            }
        }

        return admissionTypeDistribution;
    }

    private Map<String, Map<String, Double>> getSecondarySchoolTypeDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> secondarySchoolTypeDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }
                if (!secondarySchoolTypeDistribution.containsKey(student.getPeriodoDeIngresso())) {
                    secondarySchoolTypeDistribution.put(student.getPeriodoDeIngresso(), new HashMap<>());
                }
                if (!secondarySchoolTypeDistribution.get(student.getPeriodoDeIngresso()).containsKey(student.getTipoDeEnsinoMedio())) {
                    secondarySchoolTypeDistribution.get(student.getPeriodoDeIngresso()).put(student.getTipoDeEnsinoMedio(), 0.0);
                }
                secondarySchoolTypeDistribution.get(student.getPeriodoDeIngresso()).put(student.getTipoDeEnsinoMedio(),
                        secondarySchoolTypeDistribution.get(student.getPeriodoDeIngresso()).get(student.getTipoDeEnsinoMedio()) + 1);
            }
        }

        return secondarySchoolTypeDistribution;
    }

    private Map<String, Map<String, Double>> getOriginDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> originDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }

                String naturalidade = student.getNaturalidade() != null ? student.getNaturalidade() : "Desconhecido";

                originDistribution
                        .computeIfAbsent(student.getPeriodoDeIngresso(), k -> new HashMap<>())
                        .compute(student.getNaturalidade(), (k, v) -> (v == null ? 0 : v) + 1);
            }
        }
        return originDistribution;
    }

    private Map<String, Map<String, Double>> getColorDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> colorDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }

                String cor = student.getCor() != null ? student.getCor() : "Desconhecido";

                if (!colorDistribution.containsKey(student.getPeriodoDeIngresso())) {
                    colorDistribution.put(student.getPeriodoDeIngresso(), new HashMap<>());
                }
                if (!colorDistribution.get(student.getPeriodoDeIngresso()).containsKey(cor)) {
                    colorDistribution.get(student.getPeriodoDeIngresso()).put(cor, 0.0);
                }
                colorDistribution.get(student.getPeriodoDeIngresso()).put(
                        cor,
                        colorDistribution.get(student.getPeriodoDeIngresso()).get(cor) + 1
                );
            }
        }

        return colorDistribution;
    }

    private Map<String, Map<String, Double>> getDisabilitiesDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> disabilitiesDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null ||
                        !terms.contains(student.getPeriodoDeIngresso()) ||
                        student.getDeficiencias() == null ||
                        student.getDeficiencias().isEmpty()) {
                    continue;
                }

                for (String deficiencia : student.getDeficiencias()) {
                    disabilitiesDistribution
                            .computeIfAbsent(student.getPeriodoDeIngresso(), k -> new HashMap<>())
                            .merge(deficiencia, 1.0, Double::sum);
                }
            }
        }

        return disabilitiesDistribution;
    }

    public Map<String, Map<String, Double>> getAgeAtEnrollmentDistribution(
            List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> ageDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }

                int idadeNaMatricula = calcularIdadeNaMatricula(Integer.parseInt(student.getIdade()), student.getPeriodoDeIngresso());
                String ageRange = getAgeRange(String.valueOf(idadeNaMatricula));

                ageDistribution.putIfAbsent(student.getPeriodoDeIngresso(), new HashMap<>());
                Map<String, Double> periodDistribution = ageDistribution.get(student.getPeriodoDeIngresso());

                periodDistribution.putIfAbsent(ageRange, 0.0);
                periodDistribution.put(ageRange, periodDistribution.get(ageRange) + 1);
            }
        }

        return ageDistribution;
    }

    private int calcularIdadeNaMatricula(int idadeAtual, String periodoIngresso) {
        String[] partesPeriodo = periodoIngresso.split("\\.");
        int anoIngresso = Integer.parseInt(partesPeriodo[0]);
        int anoAtual = java.time.LocalDate.now().getYear();
        int diferencaAnos = anoAtual - anoIngresso;
        int idadeNaMatricula = idadeAtual - diferencaAnos;

        return idadeNaMatricula;
    }

    private Map<String, Map<String, Double>> getCreditCompletedVsFailedDistribution(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> creditDistribution = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeIngresso() == null || !terms.contains(student.getPeriodoDeIngresso())) {
                    continue;
                }

                String period = student.getPeriodoDeIngresso();
                int failed = student.getCreditosFalhados() != null ? student.getCreditosFalhados() : 0;
                int completed = student.getCreditosCompletados() != null ? student.getCreditosCompletados() : 0;

                if (!creditDistribution.containsKey(period)) {
                    creditDistribution.put(period, new HashMap<>());
                    creditDistribution.get(period).put("Completados", 0.0);
                    creditDistribution.get(period).put("Falhados", 0.0);
                }

                creditDistribution.get(period).put("Falhados", creditDistribution.get(period).get("Falhados") + failed);
                creditDistribution.get(period).put("Completados", creditDistribution.get(period).get("Completados") + completed);
            }
        }
        return creditDistribution;
    }

    private Map<String, Map<String, Double>> getEvasionStatisticsByEvasionPeriod(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> evasionStatistics = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        "ATIVO".equals(student.getSituacao())) {
                    continue;
                }

                if ("GRADUADO".equals(student.getMotivoDeEvasao()) || "REGULAR".equals(student.getMotivoDeEvasao())) {
                    continue;
                }

                evasionStatistics.putIfAbsent(student.getPeriodoDeEvasao(), new HashMap<String, Double>() {{
                    put("Soma Créditos Tentados", 0.0);
                    put("Soma Créditos Falhados", 0.0);
                    put("Soma Velocidade Média", 0.0);
                    put("Soma Períodos Completados", 0.0);
                    put("Soma CRA", 0.0);
                    put("Total Evadidos", 0.0);
                }});

                Map<String, Double> currentStats = evasionStatistics.get(student.getPeriodoDeEvasao());

                if (student.getCreditosTentados() != null) {
                    currentStats.put("Soma Créditos Tentados", currentStats.get("Soma Créditos Tentados") + student.getCreditosTentados());
                }

                if (student.getCreditosFalhados() != null) {
                    currentStats.put("Soma Créditos Falhados", currentStats.get("Soma Créditos Falhados") + student.getCreditosFalhados());
                }

                if (student.getVelocidadeMedia() != null) {
                    currentStats.put("Soma Velocidade Média", currentStats.get("Soma Velocidade Média") + student.getVelocidadeMedia());
                }

                if (student.getPeriodosCompletados() != null) {
                    currentStats.put("Soma Períodos Completados", currentStats.get("Soma Períodos Completados") + student.getPeriodosCompletados());
                }

                if (student.getNotasAcumuladas() != null && student.getCreditosDoCra() != null) {
                    if (student.getCreditosDoCra() > 0 && student.getNotasAcumuladas() > 0) {
                        double cra = student.getNotasAcumuladas() / student.getCreditosDoCra();
                        currentStats.put("Soma CRA", currentStats.get("Soma CRA") + cra);
                    }
                }

                currentStats.put("Total Evadidos", currentStats.get("Total Evadidos") + 1);
            }
        }

        evasionStatistics.forEach((period, stats) -> {
            if (stats.get("Total Evadidos") > 0) {
                stats.put("Média Créditos Tentados", stats.get("Soma Créditos Tentados") / stats.get("Total Evadidos"));
                stats.put("Média Créditos Falhados", stats.get("Soma Créditos Falhados") / stats.get("Total Evadidos"));
                stats.put("Média Velocidade Média", stats.get("Soma Velocidade Média") / stats.get("Total Evadidos"));
                stats.put("Média Períodos Completados", stats.get("Soma Períodos Completados") / stats.get("Total Evadidos"));
                stats.put("Média CRA", stats.get("Soma CRA") / stats.get("Total Evadidos"));
            }

            stats.remove("Soma Créditos Tentados");
            stats.remove("Soma Créditos Falhados");
            stats.remove("Soma Velocidade Média");
            stats.remove("Soma Períodos Completados");
            stats.remove("Soma CRA");
            stats.remove("Total Evadidos");
        });

        return evasionStatistics;
    }

    private Map<String, Map<String, Double>> getGraduationStatisticsByEvasionPeriod(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> graduationStatistics = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        student.getSituacao().equals("ATIVO")) {
                    continue;
                }

                if (!student.getMotivoDeEvasao().equals("GRADUADO")) {
                    continue;
                }

                graduationStatistics.putIfAbsent(student.getPeriodoDeEvasao(), new HashMap<>());
                Map<String, Double> currentStats = graduationStatistics.get(student.getPeriodoDeEvasao());

                currentStats.putIfAbsent("Soma Créditos Tentados", 0.0);
                currentStats.putIfAbsent("Soma Créditos Falhados", 0.0);
                currentStats.putIfAbsent("Soma Velocidade Média", 0.0);
                currentStats.putIfAbsent("Soma Períodos Completados", 0.0);
                currentStats.putIfAbsent("Soma CRA", 0.0);
                currentStats.putIfAbsent("Total Graduados", 0.0);

                if (student.getCreditosTentados() != null) {
                    currentStats.put("Soma Créditos Tentados", currentStats.get("Soma Créditos Tentados") + student.getCreditosTentados());
                }

                if (student.getCreditosFalhados() != null) {
                    currentStats.put("Soma Créditos Falhados", currentStats.get("Soma Créditos Falhados") + student.getCreditosFalhados());
                }

                if (student.getVelocidadeMedia() != null) {
                    currentStats.put("Soma Velocidade Média", currentStats.get("Soma Velocidade Média") + student.getVelocidadeMedia());
                }

                if (student.getPeriodosCompletados() != null) {
                    currentStats.put("Soma Períodos Completados", currentStats.get("Soma Períodos Completados") + student.getPeriodosCompletados());
                }

                if (student.getNotasAcumuladas() != null && student.getCreditosDoCra() != null) {
                    if (student.getCreditosDoCra() > 0 && student.getNotasAcumuladas() > 0) {
                        double cra = student.getNotasAcumuladas() / student.getCreditosDoCra();
                        currentStats.put("Soma CRA", currentStats.get("Soma CRA") + cra);
                    }
                }

                currentStats.put("Total Graduados", currentStats.get("Total Graduados") + 1);
            }
        }

        graduationStatistics.forEach((period, stats) -> {
            if (stats.get("Total Graduados") > 0) {
                stats.put("Média Créditos Tentados", stats.get("Soma Créditos Tentados") / stats.get("Total Graduados"));
                stats.put("Média Créditos Falhados", stats.get("Soma Créditos Falhados") / stats.get("Total Graduados"));
                stats.put("Média Velocidade Média", stats.get("Soma Velocidade Média") / stats.get("Total Graduados"));
                stats.put("Média Períodos Completados", stats.get("Soma Períodos Completados") / stats.get("Total Graduados"));
                stats.put("Média CRA", stats.get("Soma CRA") / stats.get("Total Graduados"));
            }

            stats.remove("Soma Créditos Tentados");
            stats.remove("Soma Créditos Falhados");
            stats.remove("Soma Velocidade Média");
            stats.remove("Soma Períodos Completados");
            stats.remove("Soma CRA");
            stats.remove("Total Graduados");
        });

        return graduationStatistics;
    }

    private Map<String, Map<String, Double>> getEvasionByColor(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> evasionByColor = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        "ATIVO".equals(student.getSituacao())) {
                    continue;
                }

                if ("GRADUADO".equals(student.getMotivoDeEvasao()) ||
                        "REGULAR".equals(student.getMotivoDeEvasao())) {
                    continue;
                }

                if (!evasionByColor.containsKey(student.getPeriodoDeEvasao())) {
                    evasionByColor.put(student.getPeriodoDeEvasao(), new HashMap<>());
                }

                String cor = student.getCor() != null ? student.getCor() : "Desconhecido";

                Map<String, Double> colorMap = evasionByColor.get(student.getPeriodoDeEvasao());
                colorMap.putIfAbsent(cor, 0.0);
                colorMap.put(cor, colorMap.get(cor) + 1);
            }
        }

        return evasionByColor;
    }

    private Map<String, Map<String, Double>> getEvasionByAge(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> evasionByAgeGroup = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        student.getSituacao().equals("ATIVO")) {
                    continue;
                }

                if (student.getMotivoDeEvasao().equals("GRADUADO") ||
                        student.getMotivoDeEvasao().equals("REGULAR")) {
                    continue;
                }

                int idadeNoMomentoDaEvasao = calcularIdadeNaEvasao(
                        Integer.parseInt(student.getIdade()),
                        student.getPeriodoDeIngresso(),
                        student.getPeriodoDeEvasao()
                );

                String faixaEtaria = getAgeRange(String.valueOf(idadeNoMomentoDaEvasao));

                evasionByAgeGroup.putIfAbsent(student.getPeriodoDeEvasao(), new HashMap<>());
                evasionByAgeGroup.get(student.getPeriodoDeEvasao())
                        .putIfAbsent(faixaEtaria, 0.0);

                evasionByAgeGroup.get(student.getPeriodoDeEvasao())
                        .put(faixaEtaria, evasionByAgeGroup.get(student.getPeriodoDeEvasao()).get(faixaEtaria) + 1);
            }
        }

        return evasionByAgeGroup;
    }

    private int calcularIdadeNaEvasao(int idadeAtual, String periodoIngresso, String periodoEvasao) {
        String[] partesPeriodoIngresso = periodoIngresso.split("\\.");
        int anoIngresso = Integer.parseInt(partesPeriodoIngresso[0]);

        String[] partesPeriodoEvasao = periodoEvasao.split("\\.");
        int anoEvasao = Integer.parseInt(partesPeriodoEvasao[0]);

        int diferencaAnos = anoEvasao - anoIngresso;
        return idadeAtual - diferencaAnos;
    }

    private Map<String, Map<String, Double>> getEvasionByGender(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> evasionByGender = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        "ATIVO".equals(student.getSituacao())) {
                    continue;
                }

                if ("GRADUADO".equals(student.getMotivoDeEvasao()) || "REGULAR".equals(student.getMotivoDeEvasao())) {
                    continue;
                }

                evasionByGender.putIfAbsent(student.getPeriodoDeEvasao(), new HashMap<>());

                String genero = student.getGenero() != null ? student.getGenero() : "Desconhecido";

                evasionByGender.get(student.getPeriodoDeEvasao())
                        .put(genero, evasionByGender.get(student.getPeriodoDeEvasao())
                                .getOrDefault(genero, 0.0) + 1);
            }
        }

        return evasionByGender;
    }


    private Map<String, Map<String, Double>> getEvasionByAdmissionType(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> evasionByAdmissionType = new HashMap<>();

        for (Course course : courses) {
            if (course.getStudents() == null) continue;

            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        "ATIVO".equals(student.getSituacao())) {
                    continue;
                }

                if ("GRADUADO".equals(student.getMotivoDeEvasao()) ||
                        "REGULAR".equals(student.getMotivoDeEvasao())) {
                    continue;
                }

                evasionByAdmissionType.putIfAbsent(student.getPeriodoDeEvasao(), new HashMap<>());

                String formaDeIngresso = student.getFormaDeIngresso() != null
                        ? student.getFormaDeIngresso()
                        : "Desconhecido";

                evasionByAdmissionType.get(student.getPeriodoDeEvasao())
                        .merge(formaDeIngresso, 1.0, Double::sum);
            }
        }

        return evasionByAdmissionType;
    }

    private Map<String, Map<String, Double>> getEvasionBySecondarySchoolType(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> evasionBySecondarySchoolType = new HashMap<>();

        for (Course course : courses) {
            if (course.getStudents() == null) {
                continue;
            }
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null ||
                        !terms.contains(student.getPeriodoDeEvasao()) ||
                        "ATIVO".equals(student.getSituacao())) {
                    continue;
                }

                if ("GRADUADO".equals(student.getMotivoDeEvasao()) ||
                        "REGULAR".equals(student.getMotivoDeEvasao())) {
                    continue;
                }

                evasionBySecondarySchoolType.putIfAbsent(student.getPeriodoDeEvasao(), new HashMap<>());

                String tipoDeEnsinoMedio = student.getTipoDeEnsinoMedio() != null ? student.getTipoDeEnsinoMedio() : "Desconhecido";

                evasionBySecondarySchoolType.get(student.getPeriodoDeEvasao())
                        .put(tipoDeEnsinoMedio, evasionBySecondarySchoolType.get(student.getPeriodoDeEvasao())
                                .getOrDefault(tipoDeEnsinoMedio, 0.0) + 1);
            }
        }

        return evasionBySecondarySchoolType;
    }

    private Map<String, Map<String, Double>> getEvasionByDisabilities(List<Course> courses, List<String> terms) {
        Map<String, Map<String, Double>> evasionByDisabilities = new HashMap<>();

        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getPeriodoDeEvasao() == null
                        || !terms.contains(student.getPeriodoDeEvasao())
                        || "ATIVO".equals(student.getSituacao())) {
                    continue;
                }

                if ("GRADUADO".equals(student.getMotivoDeEvasao())
                        || "REGULAR".equals(student.getMotivoDeEvasao())) {
                    continue;
                }

                if (student.getDeficiencias() == null || student.getDeficiencias().isEmpty()) {
                    continue;
                }

                for (String deficiencia : student.getDeficiencias()) {
                    evasionByDisabilities
                            .computeIfAbsent(student.getPeriodoDeEvasao(), k -> new HashMap<>())
                            .merge(deficiencia, 1.0, Double::sum);
                }
            }
        }

        return evasionByDisabilities;
    }

}
