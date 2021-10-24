package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.repository.StudentRepository;
import com.ufar.studentmanagementsystem.repository.StudentRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findStudents() {
        LOGGER.info("Students found");
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        LOGGER.info("Students added");
        return studentRepository.add(student);
    }

    @Override
    public Optional<Student> findStudentById(Integer id) {
        LOGGER.warn("Problem with the student no: " + id);
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        LOGGER.info("Student"+ student + " updated");
        return studentRepository.update(student);
    }

    @Override
    public void deleteStudentById(Integer id) {
        LOGGER.info("Student " + id + " is deleted");
        studentRepository.deleteById(id);
    }
}
