package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findStudents();

    Student addStudent(Student student);

    Optional<Student> findStudentById(Integer id);

    Optional<Student> updateStudent(Student student);

    void deleteStudentById(Integer id);
}
