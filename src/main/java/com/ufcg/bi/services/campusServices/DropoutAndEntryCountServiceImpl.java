package com.ufcg.bi.services.campusServices;


import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.campusDTOs.DropoutAndEntryCountDTO;
import com.ufcg.bi.models.campusModels.DropoutAndEntryCount;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.studentModels.Student;
import com.ufcg.bi.repositories.campusRepositories.DropoutAndEntryCountRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutAndEntryCountServiceImpl implements DropoutAndEntryCountService {

    @Autowired
    private DropoutAndEntryCountRepository dropoutAndEntryCountRepository;

    @Override
    public List<DropoutAndEntryCountDTO> getAllDropoutAndEntryCount() {
        List<DropoutAndEntryCount> data = dropoutAndEntryCountRepository.findAll();
        List<DropoutAndEntryCountDTO> dtos = new ArrayList<>();

        for (DropoutAndEntryCount d : data) {
            DropoutAndEntryCountDTO dto = new DropoutAndEntryCountDTO(
                d.getId(),
                d.getCodigoDoCurso(),
                d.getNomeCurso(),
                d.getStatus(),
                d.getCodigoDoSetor(),
                d.getNomeDoSetor(),
                d.getCodigoDoCampus(),
                d.getNomeDoCampus(),
                d.getPeriodo(),
                d.getQuantidadeIngressantes(),
                d.getQuantidadeEvasão(),
                d.getAno()
            );

            dtos.add(dto);
        }

        return dtos;
        
    }

    @Override
    public void createDropoutAndEntryCount(Course course, String term) {
        Map<String, Integer> counts = getDropoutAndEntryCounts(course, term);

        DropoutAndEntryCount data = new DropoutAndEntryCount(
            course.getDescricao() + " - " + term,
            course.getCodigoDoCurso(),
            course.getDescricao(),
            course.getStatus(),
            course.getCodigoDoSetor(),
            course.getNomeDoSetor(),
            course.getCampus(),
            course.getNomeDoCampus(),
            term,
            counts.getOrDefault("ingressantes", 0),
            counts.getOrDefault("evasao", 0),
            Utils.getYearFromTerm(term)
        );

        dropoutAndEntryCountRepository.save(data);
    }

    private Map<String, Integer> getDropoutAndEntryCounts(Course course, String term) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("ingressantes", 0);
        counts.put("evasao", 0);

        for (Student student : course.getStudents()) {
            if (term.equals(student.getPeriodoDeIngresso())) {
                counts.merge("ingressantes", 1, Integer::sum);
            }
            if (term.equals(student.getPeriodoDeEvasao())) {
                if (student.getPeriodoDeEvasao() == null ||
                !term.equals(student.getPeriodoDeEvasao()) ||
                "ATIVO".equals(student.getSituacao())) {
            continue;
        }

        if ("GRADUADO".equals(student.getMotivoDeEvasao()) || 
                "REGULAR".equals(student.getMotivoDeEvasao())) {
            continue;
        }

                counts.merge("evasao", 1, Integer::sum);
            }
        }

        return counts;
    }

   
}
