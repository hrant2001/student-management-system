package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import com.ufar.studentmanagementsystem.repository.UniversityRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityServiceImpl.class);

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<University> findUniversities() {
        LOGGER.info("Universities found");
        return universityRepository.findAll();
    }

    @Override
    public University addUniversity(University university) {
        LOGGER.info("University added");
        return universityRepository.add(university);
    }

    @Override
    public Optional<University> findUniversityById(Integer id) {
        LOGGER.warn("Not found with the id no: " + id);
        return universityRepository.findById(id);
    }

    @Override
    public Optional<University> updateUniversity(University university) {
        LOGGER.info("University " + university + " updated");
        return universityRepository.update(university);
    }

    @Override
    public void deleteUniversityById(Integer id) {
        LOGGER.info("University " + id + " is deleted");
        universityRepository.deleteById(id);
    }
}
