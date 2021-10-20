package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.University;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UniversityService {
    @Transactional
    University addUniversity(University university);

    List<University> findUniversities();

    Optional<University> findUniversityById(Integer id);

    @Transactional
    Optional<University> updateUniversity(University university);

    @Transactional
    void deleteUniversityById(Integer id);
}
