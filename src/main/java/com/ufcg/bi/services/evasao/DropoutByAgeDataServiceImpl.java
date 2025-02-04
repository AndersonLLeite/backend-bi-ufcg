package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.evasao.DropoutByAgeData;
import com.ufcg.bi.repositories.evasao.DropoutByAgeDataRepository;
@Service
public class DropoutByAgeDataServiceImpl implements DropoutByAgeDataService {
    @Autowired
    private DropoutByAgeDataRepository dropoutByAgeDataRepository;
    @Override
    public List<DropoutByAgeData> getAllDropoutByAgeData() {
        return dropoutByAgeDataRepository.findAll();
    }

    @Override
    public void createDropoutByAgeData(Course course, String term) {
        DropoutByAgeData dropoutByAgeData = new DropoutByAgeData(
            course.getDescricao() + " - " + term,
            course.getCodigoDoCurso(),
            course.getDescricao(),
            course.getStatus(),
            course.getCodigoDoSetor(),
            course.getNomeDoSetor(),
            course.getCampus(),
            course.getNomeDoCampus(),
            term,
            getDropoutByAge(course, term)
        );

        dropoutByAgeDataRepository.save(dropoutByAgeData);
        
    }

    private Map<String, Double> getDropoutByAge(Course course, String term) {
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
    
}
