package com.ufcg.bi.services.docentes;

import java.util.List;

import com.ufcg.bi.models.docentes.Teacher;

public interface TeacherService {
        public List<Teacher> GetAllTeachers();
        public void saveTeachers(List<Teacher> teachers) ;
        public void fetchTeachersByCampusCode(Integer campusCode);
    
} 