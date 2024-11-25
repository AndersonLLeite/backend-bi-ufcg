package com.ufcg.bi.services;

import com.ufcg.bi.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> fetchStudents(Integer courseCode);
}
