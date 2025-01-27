package com.ufcg.bi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.FilterData;
import com.ufcg.bi.repositories.FilterDataRepository;

@Service
public class FilterDataServiceImpl implements FilterDataService {
    @Autowired
    private FilterDataRepository filterRepository;

    @Override
    public List<FilterData> getAllFilterData() {
        return filterRepository.findAll();
    }

    @Override
    public void createFilterData(Course course, String term) {
        FilterData filterData = new FilterData(
            course.getDescricao() + " - " + term,
            course.getCodigoDoCurso(),
            course.getDescricao(),
            course.getStatus(),
            course.getCodigoDoSetor(),
            course.getNomeDoSetor(),
            course.getCampus(),
            course.getNomeDoCampus(),
            term
        );
        filterRepository.save(filterData);
    }

    
  
}
