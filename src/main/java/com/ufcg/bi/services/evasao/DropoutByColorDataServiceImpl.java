package com.ufcg.bi.services.evasao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.evasao.DropoutByColorData;
import com.ufcg.bi.repositories.evasao.DropoutByColorDataRepository;
import com.ufcg.bi.utils.Utils;
@Service
public class DropoutByColorDataServiceImpl implements DropoutByColorDataService {
    @Autowired
    private DropoutByColorDataRepository dropoutByColorDataRepository;
  
    @Override
    public List<DropoutByColorData> getAllDropoutByColorData() {
        return dropoutByColorDataRepository.findAll();
        
    }

    @Override
    public void createDropoutByColorData(Course course, String term) {
        DropoutByColorData dropoutByColorData = new DropoutByColorData(
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
            getDropoutByColor(course, term)
        );

        dropoutByColorDataRepository.save(dropoutByColorData);
      
    }
    

    private Map<String, Double> getDropoutByColor(Course course, String term) {
    Map<String, Double> evasionByColor = new HashMap<>();

    if (course.getStudents() == null) return evasionByColor;

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

        // Define a cor do estudante ou usa "Desconhecido" caso seja nula
        String cor = student.getCor() != null ? student.getCor() : "Desconhecido";

        // Incrementa a contagem de evasão por cor
        evasionByColor.merge(cor, 1.0, Double::sum);
    }

    return evasionByColor;
}

}
