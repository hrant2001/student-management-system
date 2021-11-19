package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;

import java.sql.Blob;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student addImage(Integer id);

    List<Student> findStudents();

    Student findStudentById(Integer id);

    Student updateStudent(Student student);

    void deleteStudentById(Integer id);

    void deleteImageByStudentId(Integer id);
}
