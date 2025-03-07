package com.ufcg.bi.services.studentServices;

import java.util.List;

import com.ufcg.bi.models.studentModels.Student;

public interface StudentService {
    List<Student> fetchStudents(Integer courseCode);
}
