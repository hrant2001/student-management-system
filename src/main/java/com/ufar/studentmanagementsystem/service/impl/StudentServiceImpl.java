package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.exception.NotFoundException;
import com.ufar.studentmanagementsystem.exception.NotValidException;
import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.model.User;
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
        if (!StudentValidation.IsValid(student)) {
            LOGGER.warn("The student " + student + " is not valid");
            throw new NotValidException("The student is not valid");
        }


        LOGGER.info("The student " + student + " is added");
        return studentRepository.add(student);
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
            LOGGER.warn("The student " + id + " is not found");
            throw new NotFoundException("The student with id " + id + " is not found");
        }

        return student;
    }


    @Override
    @Transactional
    public Student updateStudent(Student student) {
        if (student.getId() == null || student.getId() <= 0 || !StudentValidation.IsValid(student)) {
            LOGGER.warn("Invalid student");
            throw new NotValidException("The student is not valid");
        }

        Student updatingStudent = studentRepository.findById(student.getId()).orElse(null);

        if (updatingStudent == null) {
            LOGGER.warn("The student " + student.getId() + " is not found");
            throw new NotFoundException("The user with id " + student.getId() + " is not found");
        }
        LOGGER.info("Student " + student + " is updated");
        return studentRepository.update(student).get();
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {
        Student updatingStudent = studentRepository.findById(id).orElse(null);
        if (updatingStudent == null) {
            LOGGER.warn("Student no: " + id + " is not found");
            throw new NotFoundException("The student with id " + id + " is not found");
        }
        LOGGER.info("Student " + id + " is deleted");
        studentRepository.deleteById(id);
    }
}
