package com.ufcg.bi.services.campus;


import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Student;
import com.ufcg.bi.models.campus.DropoutAndEntryCount;
import com.ufcg.bi.models.course.Course;
import com.ufcg.bi.repositories.campus.DropoutAndEntryCountRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class DropoutAndEntryCountServiceImpl implements DropoutAndEntryCountService {

    @Autowired
    private DropoutAndEntryCountRepository dropoutAndEntryCountRepository;

    @Override
    public List<DropoutAndEntryCount> getAllDropoutAndEntryCount() {
        return dropoutAndEntryCountRepository.findAll();
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
            Utils.getYearFromTerm(term),
            counts.getOrDefault("ingressantes", 0),
            counts.getOrDefault("evasao", 0)

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
                counts.merge("evasao", 1, Integer::sum);
            }
        }

        return counts;
    }

   
}
