package com.ufcg.bi.services.filterServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.bi.DTO.filterDtos.FilterDataDTO;
import com.ufcg.bi.models.courseModels.Course;
import com.ufcg.bi.models.filterModels.FilterData;
import com.ufcg.bi.repositories.StudentRepositories.FilterDataRepository;
import com.ufcg.bi.utils.Utils;

@Service
public class FilterDataServiceImpl implements FilterDataService {
    @Autowired
    private FilterDataRepository filterRepository;

    @Override
    public List<FilterDataDTO> getAllFilterData() {
        List<FilterData> filterData = filterRepository.findAll();
        List<FilterDataDTO> filterDataDTOs = new ArrayList<>();
        for (FilterData filter : filterData) {
            FilterDataDTO filterDataDTO = new FilterDataDTO(
                filter.getId(),
                filter.getCodigoDoCurso(),
                filter.getCurso(),
                filter.getStatus(),
                filter.getCodigoDoSetor(),
                filter.getSetor(),
                filter.getCodigoDoCampus(),
                filter.getCampus(),
                filter.getPeriodo(),
                filter.getAno()
            );
            filterDataDTOs.add(filterDataDTO);
        }
        return filterDataDTOs;
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
            term,
            Utils.getYearFromTerm(term)
        );
        filterRepository.save(filterData);
    }

    
  
}
