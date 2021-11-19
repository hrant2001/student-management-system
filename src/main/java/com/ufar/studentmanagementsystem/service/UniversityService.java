package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.University;

import java.util.List;

public interface UniversityService {
    University addUniversity(University university);

    List<University> findUniversities();

    University findUniversityById(Integer id);

    University updateUniversity(University university);

    void deleteUniversityById(Integer id);
}
