package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> findStudents();

    Student findStudentById(Integer id);

    Student updateStudent(Student student);

    void deleteStudentById(Integer id);
}
