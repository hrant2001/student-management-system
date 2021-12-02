package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.exception.NotFoundException;
import com.ufar.studentmanagementsystem.exception.NotValidException;
import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.repository.StudentRepository;
import com.ufar.studentmanagementsystem.service.StudentService;
import com.ufar.studentmanagementsystem.service.validation.StudentValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        if (!StudentValidation.isValid(student)) {
            LOGGER.warn("The student {} is not valid", student);
            throw new NotValidException("The student is not valid");
        }

        LOGGER.info("The student {} is added", student);
        return studentRepository.add(student);
    }

    @Override
    @Transactional
    public Student addImage(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            LOGGER.warn("The student {} is not found", id);
            throw new NotFoundException("The student with id " + id + " is not found");
        }
        return studentRepository.addImage(id);
    }

    @Override
    public List<Student> findStudents() {
        LOGGER.info("Students are found");
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            LOGGER.warn("The student {} is not found", id);
            throw new NotFoundException("The student with id " + id + " is not found");
        }

        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        if (!StudentValidation.isValid(student) || student.getId() == null || student.getId() <= 0) {
            LOGGER.warn("Invalid student");
            throw new NotValidException("The student is not valid");
        }

        Student updatingStudent = studentRepository.findById(student.getId()).orElse(null);

        if (updatingStudent == null) {
            LOGGER.warn("The student {} is not found", student.getId());
            throw new NotFoundException("The user with id " + student.getId() + " is not found");
        }
        LOGGER.info("Student {} is updated", student);
        return studentRepository.update(student).get();
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {
        Student deletingStudent = studentRepository.findById(id).orElse(null);
        if (deletingStudent == null) {
            LOGGER.warn("Student no: {} is not found", id);
            throw new NotFoundException("The student with id " + id + " is not found");
        }
        LOGGER.info("Student {} is deleted", id);
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteImageByStudentId(Integer id) {
        Student deletingStudent = studentRepository.findById(id).orElse(null);
        if (deletingStudent == null) {
            LOGGER.warn("Student no: {} is not found", id);
            throw new NotFoundException("The student with id " + id + " is not found");
        }
        LOGGER.info("Student {} is deleted", id);
        studentRepository.deleteImageByStudentId(id);
    }
}
