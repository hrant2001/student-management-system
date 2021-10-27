package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> findStudents();

    Optional<Student> findStudentById(Integer id);

    Optional<Student> updateStudent(Student student);

    void deleteStudentById(Integer id);
}
