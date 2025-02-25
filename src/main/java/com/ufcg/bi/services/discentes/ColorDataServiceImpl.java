package com.ufcg.bi.services.discentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.models.discentes.ColorData;
import com.ufcg.bi.repositories.discentes.ColorDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class ColorDataServiceImpl implements ColorDataService {
    @Autowired
    private ColorDataRepository colorDataRepository;
    @Override
    public List<ColorData> getAllColorData() {
        return colorDataRepository.findAll();
    }


    @Override
    public void createColorData(Course course, String term) {
        ColorData colorData = new ColorData(
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
            getColorDistribution(course, term)
        );

        colorDataRepository.save(colorData);
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
    
}
