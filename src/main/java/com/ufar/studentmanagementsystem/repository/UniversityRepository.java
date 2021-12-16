package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository {
    University add(University university);

    List<University> findAll();

    Optional<University> findById(Integer id);

    Optional<University> update(University university);

    void deleteById(Integer id);
}
