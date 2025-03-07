package com.ufcg.bi.services.teacherServices;

import java.util.List;

import com.ufcg.bi.DTO.teacherDTOs.TeacherDTO;
import com.ufcg.bi.models.TeacherModels.Teacher;

public interface TeacherService {
        public List<TeacherDTO> GetAllTeachers();
        public void fetchTeachers();
    
} 