package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityService {
    List<University> findUniversities();

    University addUniversity(University university);

    Optional<University> findUniversityById(Integer id);

    Optional<University> updateUniversity(University university);

    void deleteUniversityById(Integer id);
}
