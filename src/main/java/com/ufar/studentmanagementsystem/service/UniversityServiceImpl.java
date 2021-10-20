package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import com.ufar.studentmanagementsystem.repository.UniversityRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<University> findUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public University addUniversity(University university) {
        return universityRepository.add(university);
    }

    @Override
    public Optional<University> findUniversityById(Integer id) {
        return universityRepository.findById(id);
    }

    @Override
    public Optional<University> updateUniversity(University university) {
        return universityRepository.update(university);
    }

    @Override
    public void deleteUniversityById(Integer id) {
        universityRepository.deleteById(id);
    }
}
