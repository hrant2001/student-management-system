package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.repository.StudentRepository;
import com.ufar.studentmanagementsystem.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        LOGGER.info("Student " + student + " is added");
        return studentRepository.add(student);
    }

    @Override
    public List<Student> findStudents() {
        LOGGER.info("Students are found");
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(Integer id) {
        LOGGER.warn("Student by the id: " + id + "is not found");
        return studentRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Student> updateStudent(Student student) {
        LOGGER.info("Student " + student + " is updated");
        return studentRepository.update(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {
        LOGGER.info("Student no: " + id + " is deleted");
        studentRepository.deleteById(id);
    }
}
