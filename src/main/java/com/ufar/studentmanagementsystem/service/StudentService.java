package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface StudentService {
    @Transactional
    Student addStudent(Student student);

    List<Student> findStudents();

    Optional<Student> findStudentById(Integer id);

    @Transactional
    Optional<Student> updateStudent(Student student);

    @Transactional
    void deleteStudentById(Integer id);
}
