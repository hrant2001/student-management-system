package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> findUniversities() {
        return universityRepository.findAll();
    }

    public University addUniversity(University university) {
        return universityRepository.add(university);
    }

    public Optional<University> findUniversityById(Integer id) {
        return universityRepository.findById(id);
    }

    public Optional<University> updateUniversity(University university) {
        return universityRepository.update(university);
    }

    public void deleteUniversityById(Integer id) {
        universityRepository.deleteById(id);
    }
}
