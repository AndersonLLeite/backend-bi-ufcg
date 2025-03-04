package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.evasao.DropoutByAgeData;
import com.ufcg.bi.repositories.evasao.DropoutByAgeDataRepository;
import com.ufcg.bi.utils.Utils;
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
            Utils.getYearFromTerm(term),
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
               calcularIdadeNoIngresso(Integer.parseInt(student.getIdade()), student.getPeriodoDeIngresso()),
                student.getPeriodoDeIngresso(),
                student.getPeriodoDeEvasao()
        );

        // Determina a faixa etária
        String faixaEtaria = Utils.getAgeRange(String.valueOf(idadeNoMomentoDaEvasao));

        // Atualiza o contador para a faixa etária
        evasionByAgeGroup.put(faixaEtaria, evasionByAgeGroup.getOrDefault(faixaEtaria, 0.0) + 1);
    }

    return evasionByAgeGroup;
}

private int calcularIdadeNoIngresso(int idadeAtual, String periodoIngresso) {
    String[] partesPeriodo = periodoIngresso.split("\\.");
    int anoIngresso = Integer.parseInt(partesPeriodo[0]);
    int anoAtual = java.time.LocalDate.now().getYear();
    int diferencaAnos = anoAtual - anoIngresso;
    int idadeNoIngresso = idadeAtual - diferencaAnos;

    return idadeNoIngresso;
}

private int calcularIdadeNaEvasao(int idadeNoIngresso, String periodoIngresso, String periodoEvasao) {
    String[] partesPeriodoIngresso = periodoIngresso.split("\\.");
    int anoIngresso = Integer.parseInt(partesPeriodoIngresso[0]);

    String[] partesPeriodoEvasao = periodoEvasao.split("\\.");
    int anoEvasao = Integer.parseInt(partesPeriodoEvasao[0]);

    int diferencaAnos = anoEvasao - anoIngresso;
    return idadeNoIngresso + diferencaAnos; 
}

    
}
