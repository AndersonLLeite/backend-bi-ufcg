package com.ufcg.bi.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Data2;
import com.ufcg.bi.models.Student;
//import com.ufcg.bi.repositories.DataRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DataService2 {

    // private final DataRepository dataRepository;

    // public DataService2(DataRepository dataRepository) {
    //     this.dataRepository = dataRepository;
    // }

    // @Transactional
    // public void saveAll(List<Data2> dataList) {
    //     dataRepository.saveAll(dataList);
    // }

    // public List<Data2> getAllData() {
    //     return dataRepository.findAll();
    // }

    // @Transactional
    // public void updateDataFromApi(Data2 newData) {
    //     // Verifica se o registro já existe no banco
    //    // Optional<Data2> optionalExistingData = dataRepository.findById(newData.getId());

    //     if (optionalExistingData.isPresent()) {
    //         // Atualiza os dados existentes
    //         Data2 existingData = optionalExistingData.get();

    //         // Exemplo de atualização de campos
    //         existingData.setCodigoDoCurso(newData.getCodigoDoCurso());
    //         existingData.setCurso(newData.getCurso());
    //         existingData.setStatus(newData.getStatus());
    //         existingData.setCodigoDoSetor(newData.getCodigoDoSetor());
    //         existingData.setSetor(newData.getSetor());
    //         existingData.setCodigoDoCampus(newData.getCodigoDoCampus());
    //         existingData.setCampus(newData.getCampus());
    //         existingData.setPeriodo(newData.getPeriodo());

    //         // Atualização de mapas com lógica para preservar, atualizar ou adicionar novos valores
    //         mergeMaps(existingData.getSexo(), newData.getSexo());
    //         mergeMaps(existingData.getIdade(), newData.getIdade());
    //         // mergeMaps(existingData.getPoliticaAfirmativa(), newData.getPoliticaAfirmativa());
    //         // mergeMaps(existingData.getInactivityReasonDistribution(), newData.getInactivityReasonDistribution());
    //         // Repita para os demais mapas...

    //         // Salva as mudanças no banco
    //    //     dataRepository.save(existingData);
    //     } else {
    //         // Insere um novo registro se ele não existir
    //   //      dataRepository.save(newData);
    //     }
    // }

    private void mergeMaps(Map<String, Double> existingMap, Map<String, Double> newMap) {
        if (existingMap == null || newMap == null) return;

        newMap.forEach((key, value) -> existingMap.put(key, value));
    }
    
    public Data2 createData(Course courseProcessed, String term) {
        Map<String, Double> genderDistribution = getGenderDistribution(courseProcessed, term);
        Map<String, Double> ageDistribution = getAgeDistribution(courseProcessed, term);
        Map<String, Double> affirmativePolicyDistribution = getAffirmativePolicyDistribution(courseProcessed, term);
        Map<String, Double> inactivityReasonDistribution = getInactivityReasonDistribution(courseProcessed, term);
        Map<String, Double> admissionTypeDistribution = getAdmissionTypeDistribution(courseProcessed, term);
        Map<String, Double> secondarySchoolTypeDistribution = getSecondarySchoolTypeDistribution(courseProcessed, term);
        Map<String, Double> colorDistribution = getColorDistribution(courseProcessed, term);
        Map<String, Double> disabilitiesDistribution = getDisabilitiesDistribution(courseProcessed, term);
        Map<String, Double> ageAtEnrollmentDistribution = getAgeAtEnrollmentDistribution(courseProcessed, term);
        Map<String, Double> evasionStatisticsByEvasionPeriod = getEvasionStatisticsByEvasionPeriod(courseProcessed, term);
        Map<String, Double> graduationStatisticsByEvasionPeriod = getGraduationStatisticsByEvasionPeriod(courseProcessed, term);
        Map<String, Double> evasionByAge = getEvasionByAge(courseProcessed, term);
        Map<String, Double> evasionByGender = getEvasionByGender(courseProcessed, term);
        Map<String, Double> evasionByAdmissionType = getEvasionByAdmissionType(courseProcessed, term);
        Map<String, Double> evasionBySecondarySchoolType = getEvasionBySecondarySchoolType(courseProcessed, term);
        Map<String, Double> evasionByDisabilities = getEvasionByDisabilities(courseProcessed, term);

        Data2 data = new Data2(courseProcessed.getDescricao() + " - " + term, courseProcessed.getCodigoDoCurso(), courseProcessed.getDescricao(), courseProcessed.getStatus(), courseProcessed.getCodigoDoSetor(), courseProcessed.getNomeDoSetor(), courseProcessed.getCampus(), courseProcessed.getNomeDoCampus(), term, genderDistribution, ageDistribution, affirmativePolicyDistribution, inactivityReasonDistribution, admissionTypeDistribution, secondarySchoolTypeDistribution, colorDistribution, disabilitiesDistribution, ageAtEnrollmentDistribution, evasionStatisticsByEvasionPeriod, graduationStatisticsByEvasionPeriod, evasionByAge, evasionByGender, evasionByAdmissionType, evasionBySecondarySchoolType, evasionByDisabilities);
        return data;
    }

    private Map<String, Double> getGenderDistribution(Course course, String term) {
    Map<String, Double> genderDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !term.equals(student.getPeriodoDeIngresso())) {
            continue;
        }
        genderDistribution.putIfAbsent(student.getGenero(), 0.0);
        genderDistribution.put(student.getGenero(), genderDistribution.get(student.getGenero()) + 1);
    }

    return genderDistribution;
}

private Map<String, Double> getAgeDistribution(Course course, String term) {
    Map<String, Double> ageDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !term.equals(student.getPeriodoDeIngresso())) {
            continue;
        }
        String ageRange = getAgeRange(student.getIdade());
        ageDistribution.putIfAbsent(ageRange, 0.0);
        ageDistribution.put(ageRange, ageDistribution.get(ageRange) + 1);
    }

    return ageDistribution;
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

private Map<String, Double> getAffirmativePolicyDistribution(Course course, String term) {
    Map<String, Double> affirmativePolicyDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeIngresso() == null || !term.equals(student.getPeriodoDeIngresso())) {
            continue;
        }
        String policy = student.getPoliticaAfirmativa();
        affirmativePolicyDistribution.putIfAbsent(policy, 0.0);
        affirmativePolicyDistribution.put(policy, affirmativePolicyDistribution.get(policy) + 1);
    }

    return affirmativePolicyDistribution;
}

private Map<String, Double> getInactivityReasonDistribution(Course course, String term) {
    Map<String, Double> inactivityReasonDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para ser considerado
        if (student.getPeriodoDeIngresso() == null ||
                student.getPeriodoDeEvasao() == null ||
                !student.getPeriodoDeIngresso().equals(student.getPeriodoDeEvasao()) ||
                !student.getPeriodoDeIngresso().equals(term) ||
                student.getMotivoDeEvasao() == null) {
            continue;
        }

        // Adiciona ou atualiza o motivo da evasão no Map
        inactivityReasonDistribution.put(
            student.getMotivoDeEvasao(), 
            inactivityReasonDistribution.getOrDefault(student.getMotivoDeEvasao(), 0.0) + 1
        );
    }

    return inactivityReasonDistribution;
}

private Map<String, Double> getAdmissionTypeDistribution(Course course, String term) {
    Map<String, Double> admissionTypeDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para ser considerado
        if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
            continue;
        }

        // Adiciona ou atualiza o tipo de admissão no Map
        admissionTypeDistribution.merge(student.getFormaDeIngresso(), 1.0, Double::sum);
    }

    return admissionTypeDistribution;
}


private Map<String, Double> getSecondarySchoolTypeDistribution(Course course, String term) {
    Map<String, Double> secondarySchoolTypeDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para ser considerado
        if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
            continue;
        }

        // Adiciona ou atualiza o tipo de ensino médio no Map
        secondarySchoolTypeDistribution.merge(student.getTipoDeEnsinoMedio(), 1.0, Double::sum);
    }

    return secondarySchoolTypeDistribution;
}

private Map<String, Double> getColorDistribution(Course course, String term) {
    Map<String, Double> colorDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para ser considerado
        if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
            continue;
        }

        // Determina a cor do estudante, padrão para "Desconhecido" caso seja nulo
        String cor = student.getCor() != null ? student.getCor() : "Desconhecido";

        // Adiciona ou atualiza a contagem da cor no Map
        colorDistribution.merge(cor, 1.0, Double::sum);
    }

    return colorDistribution;
}

private Map<String, Double> getDisabilitiesDistribution(Course course, String term) {
    Map<String, Double> disabilitiesDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para ser considerado
        if (student.getPeriodoDeIngresso() == null ||
                !student.getPeriodoDeIngresso().equals(term) ||
                student.getDeficiencias() == null ||
                student.getDeficiencias().isEmpty()) {
            continue;
        }

        // Processa cada deficiência do estudante e atualiza o Map
        for (String deficiencia : student.getDeficiencias()) {
            disabilitiesDistribution.merge(deficiencia, 1.0, Double::sum);
        }
    }

    return disabilitiesDistribution;
}

public Map<String, Double> getAgeAtEnrollmentDistribution(Course course, String term) {
    Map<String, Double> ageDistribution = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para ser considerado
        if (student.getPeriodoDeIngresso() == null || !student.getPeriodoDeIngresso().equals(term)) {
            continue;
        }

        // Calcula a idade na matrícula e determina a faixa etária
        int idadeNaMatricula = calcularIdadeNaMatricula(Integer.parseInt(student.getIdade()), student.getPeriodoDeIngresso());
        String ageRange = getAgeRange(String.valueOf(idadeNaMatricula));

        // Atualiza a contagem da faixa etária no Map
        ageDistribution.merge(ageRange, 1.0, Double::sum);
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

private Map<String, Double> getEvasionStatisticsByEvasionPeriod(Course course, String term) {
    Map<String, Double> evasionStatistics = new HashMap<>();

    double somaCreditosTentados = 0.0;
    double somaCreditosFalhados = 0.0;
    double somaVelocidadeMedia = 0.0;
    double somaPeriodosCompletados = 0.0;
    double somaCRA = 0.0;
    double totalEvadidos = 0.0;

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeEvasao() == null ||
                !student.getPeriodoDeEvasao().equals(term) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        if (student.getCreditosTentados() != null) {
            somaCreditosTentados += student.getCreditosTentados();
        }

        if (student.getCreditosFalhados() != null) {
            somaCreditosFalhados += student.getCreditosFalhados();
        }

        if (student.getVelocidadeMedia() != null) {
            somaVelocidadeMedia += student.getVelocidadeMedia();
        }

        if (student.getPeriodosCompletados() != null) {
            somaPeriodosCompletados += student.getPeriodosCompletados();
        }

        if (student.getNotasAcumuladas() != null && student.getCreditosDoCra() != null) {
            if (student.getCreditosDoCra() > 0 && student.getNotasAcumuladas() > 0) {
                double cra = student.getNotasAcumuladas() / student.getCreditosDoCra();
                somaCRA += cra;
            }
        }

        totalEvadidos += 1;
    }

    if (totalEvadidos > 0) {
        evasionStatistics.put("Média Créditos Tentados", somaCreditosTentados / totalEvadidos);
        evasionStatistics.put("Média Créditos Falhados", somaCreditosFalhados / totalEvadidos);
        evasionStatistics.put("Média Velocidade Média", somaVelocidadeMedia / totalEvadidos);
        evasionStatistics.put("Média Períodos Completados", somaPeriodosCompletados / totalEvadidos);
        evasionStatistics.put("Média CRA", somaCRA / totalEvadidos);
    }

    return evasionStatistics;
}


private Map<String, Double> getGraduationStatisticsByEvasionPeriod(Course course, String term) {
    Map<String, Double> graduationStatistics = new HashMap<>();

    // Inicializa as estatísticas
    graduationStatistics.put("Soma Créditos Tentados", 0.0);
    graduationStatistics.put("Soma Créditos Falhados", 0.0);
    graduationStatistics.put("Soma Velocidade Média", 0.0);
    graduationStatistics.put("Soma Períodos Completados", 0.0);
    graduationStatistics.put("Soma CRA", 0.0);
    graduationStatistics.put("Total Graduados", 0.0);

    for (Student student : course.getStudents()) {
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if (!"GRADUADO".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        // Atualiza as estatísticas
        if (student.getCreditosTentados() != null) {
            graduationStatistics.put(
                "Soma Créditos Tentados",
                graduationStatistics.get("Soma Créditos Tentados") + student.getCreditosTentados()
            );
        }

        if (student.getCreditosFalhados() != null) {
            graduationStatistics.put(
                "Soma Créditos Falhados",
                graduationStatistics.get("Soma Créditos Falhados") + student.getCreditosFalhados()
            );
        }

        if (student.getVelocidadeMedia() != null) {
            graduationStatistics.put(
                "Soma Velocidade Média",
                graduationStatistics.get("Soma Velocidade Média") + student.getVelocidadeMedia()
            );
        }

        if (student.getPeriodosCompletados() != null) {
            graduationStatistics.put(
                "Soma Períodos Completados",
                graduationStatistics.get("Soma Períodos Completados") + student.getPeriodosCompletados()
            );
        }

        if (student.getNotasAcumuladas() != null && student.getCreditosDoCra() != null) {
            if (student.getCreditosDoCra() > 0 && student.getNotasAcumuladas() > 0) {
                double cra = student.getNotasAcumuladas() / student.getCreditosDoCra();
                graduationStatistics.put(
                    "Soma CRA",
                    graduationStatistics.get("Soma CRA") + cra
                );
            }
        }

        graduationStatistics.put(
            "Total Graduados",
            graduationStatistics.get("Total Graduados") + 1
        );
    }

    // Calcula as médias
    double totalGraduados = graduationStatistics.get("Total Graduados");
    if (totalGraduados > 0) {
        graduationStatistics.put("Média Créditos Tentados", graduationStatistics.get("Soma Créditos Tentados") / totalGraduados);
        graduationStatistics.put("Média Créditos Falhados", graduationStatistics.get("Soma Créditos Falhados") / totalGraduados);
        graduationStatistics.put("Média Velocidade Média", graduationStatistics.get("Soma Velocidade Média") / totalGraduados);
        graduationStatistics.put("Média Períodos Completados", graduationStatistics.get("Soma Períodos Completados") / totalGraduados);
        graduationStatistics.put("Média CRA", graduationStatistics.get("Soma CRA") / totalGraduados);
    }

    // Remove os valores de soma que não são necessários no resultado final
    graduationStatistics.remove("Soma Créditos Tentados");
    graduationStatistics.remove("Soma Créditos Falhados");
    graduationStatistics.remove("Soma Velocidade Média");
    graduationStatistics.remove("Soma Períodos Completados");
    graduationStatistics.remove("Soma CRA");

    return graduationStatistics;
}

private Map<String, Double> getEvasionByAge(Course course, String term) {
    Map<String, Double> evasionByAgeGroup = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para a análise
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        // Calcula a idade no momento da evasão
        int idadeNoMomentoDaEvasao = calcularIdadeNaEvasao(
                Integer.parseInt(student.getIdade()),
                student.getPeriodoDeIngresso(),
                student.getPeriodoDeEvasao()
        );

        // Determina a faixa etária
        String faixaEtaria = getAgeRange(String.valueOf(idadeNoMomentoDaEvasao));

        // Atualiza o contador para a faixa etária
        evasionByAgeGroup.put(faixaEtaria, evasionByAgeGroup.getOrDefault(faixaEtaria, 0.0) + 1);
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

private Map<String, Double> getEvasionByGender(Course course, String term) {
    Map<String, Double> evasionByGender = new HashMap<>();

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios para análise
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        // Determina o gênero, usando "Desconhecido" como padrão
        String genero = student.getGenero() != null ? student.getGenero() : "Desconhecido";

        // Atualiza o contador para o gênero
        evasionByGender.put(genero, evasionByGender.getOrDefault(genero, 0.0) + 1);
    }

    return evasionByGender;
}

private Map<String, Double> getEvasionByAdmissionType(Course course, String term) {
    Map<String, Double> evasionByAdmissionType = new HashMap<>();

    if (course.getStudents() == null) return evasionByAdmissionType;

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        // Determina a forma de ingresso, usando "Desconhecido" como padrão
        String formaDeIngresso = student.getFormaDeIngresso() != null 
                ? student.getFormaDeIngresso() 
                : "Desconhecido";

        // Atualiza o contador para a forma de ingresso
        evasionByAdmissionType.merge(formaDeIngresso, 1.0, Double::sum);
    }

    return evasionByAdmissionType;
}

private Map<String, Double> getEvasionBySecondarySchoolType(Course course, String term) {
    Map<String, Double> evasionBySecondarySchoolType = new HashMap<>();

    if (course.getStudents() == null) return evasionBySecondarySchoolType;

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        // Determina o tipo de ensino médio, usando "Desconhecido" como padrão
        String tipoDeEnsinoMedio = student.getTipoDeEnsinoMedio() != null 
                ? student.getTipoDeEnsinoMedio() 
                : "Desconhecido";

        // Atualiza o contador para o tipo de ensino médio
        evasionBySecondarySchoolType.merge(tipoDeEnsinoMedio, 1.0, Double::sum);
    }

    return evasionBySecondarySchoolType;
}

private Map<String, Double> getEvasionByDisabilities(Course course, String term) {
    Map<String, Double> evasionByDisabilities = new HashMap<>();

    if (course.getStudents() == null) return evasionByDisabilities;

    for (Student student : course.getStudents()) {
        // Verifica se o estudante atende aos critérios
        if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

        // Verifica se o estudante possui deficiências
        if (student.getDeficiencias() == null || student.getDeficiencias().isEmpty()) {
            continue;
        }

        // Atualiza a contagem de evasões por tipo de deficiência
        for (String deficiencia : student.getDeficiencias()) {
            evasionByDisabilities.merge(deficiencia, 1.0, Double::sum);
        }
    }

    return evasionByDisabilities;
}





}
