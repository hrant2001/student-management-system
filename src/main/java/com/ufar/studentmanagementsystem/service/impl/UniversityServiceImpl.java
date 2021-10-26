package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import com.ufar.studentmanagementsystem.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UniversityServiceImpl implements UniversityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityServiceImpl.class);
    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    @Transactional
    public University addUniversity(University university) {
        LOGGER.info("University is added");
        return universityRepository.add(university);
    }

    @Override
    public List<University> findUniversities() {
        LOGGER.info("Universities are found");
        return universityRepository.findAll();
    }

    @Override
    public Optional<University> findUniversityById(Integer id) {
        LOGGER.warn("University by the id " + id + " is not found");
        return universityRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<University> updateUniversity(University university) {
        LOGGER.info("University " + university + "is updated");
        return universityRepository.update(university);
    }

    @Override
    @Transactional
    public void deleteUniversityById(Integer id) {
        LOGGER.info("University no: " + id + " is deleted");
        universityRepository.deleteById(id);
    }
}
