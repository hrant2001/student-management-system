package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.exception.NotFoundException;
import com.ufar.studentmanagementsystem.exception.NotValidException;
import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import com.ufar.studentmanagementsystem.service.UniversityService;
import com.ufar.studentmanagementsystem.service.validation.UniversityValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UniversityServiceImpl implements UniversityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    @Transactional
    public University addUniversity(University university) {
        if (!UniversityValidation.isValid(university)) {
            LOGGER.warn("The university {} is not valid", university);
            throw new NotValidException("The university is not valid");
        }

        LOGGER.info("The university {} is added", university);
        return universityRepository.add(university);
    }

    @Override
    public List<University> findUniversities() {
        LOGGER.info("Universities are found");
        return universityRepository.findAll();
    }

    @Override
    public University findUniversityById(Integer id) {
        University university = universityRepository.findById(id).orElse(null);
        if (university == null) {
            LOGGER.warn("The university {} is not found", id);
            throw new NotFoundException("The university with id " + id + " is not found");
        }

        return university;
    }


    @Override
    @Transactional
    public University updateUniversity(University university) {
        if (!UniversityValidation.isValid(university) || university.getId() == null || university.getId() <= 0) {
            LOGGER.warn("Invalid university");
            throw new NotValidException("The university is not valid");
        }

        University updatingUniversity = universityRepository.findById(university.getId()).orElse(null);

        if (updatingUniversity == null) {
            LOGGER.warn("The university {} is not found", university.getId());
            throw new NotFoundException("The university with id " + university.getId() + " is not found");
        }
        LOGGER.info("University {} is updated", university);
        return universityRepository.update(university).get();
    }

    @Override
    @Transactional
    public void deleteUniversityById(Integer id) {
        University deletingUniversity = universityRepository.findById(id).orElse(null);
        if (deletingUniversity == null) {
            LOGGER.warn("University no: " + id + " is not found");
            throw new NotFoundException("The University with id " + id + " is not found");
        }
        LOGGER.info("University {} is deleted", id);
        universityRepository.deleteById(id);
    }
}
