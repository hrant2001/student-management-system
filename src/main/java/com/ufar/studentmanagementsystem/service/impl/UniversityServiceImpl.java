package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import com.ufar.studentmanagementsystem.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    @Transactional
    public University addUniversity(University university) {
        return universityRepository.add(university);
    }

    @Override
    public List<University> findUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public Optional<University> findUniversityById(Integer id) {
        return universityRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<University> updateUniversity(University university) {
        return universityRepository.update(university);
    }

    @Override
    @Transactional
    public void deleteUniversityById(Integer id) {
        universityRepository.deleteById(id);
    }
}
