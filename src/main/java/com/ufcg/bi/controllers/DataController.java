package com.ufcg.bi.controllers;

import com.ufcg.bi.models.Course;
import com.ufcg.bi.models.Data;
import com.ufcg.bi.models.RequestData;
import com.ufcg.bi.services.CourseService;
import com.ufcg.bi.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DataService dataService;

    @GetMapping("/data")
    public Data getData(@RequestBody RequestData requestData) {
        List<Course> courses = courseService.getCourses(requestData.getCourseCodes());
        return dataService.getData(courses, requestData.getTerms());

    }
}
