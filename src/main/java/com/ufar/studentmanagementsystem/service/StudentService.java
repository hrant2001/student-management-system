package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student addImage(Integer id, MultipartFile image);

    List<Student> findStudents();

    List<Student> findStudentsFromUniversity(Integer id);

    Student findStudentById(Integer id);

    Student updateStudent(Student student);

    void deleteStudentById(Integer id);

    void deleteImageByStudentId(Integer id);
}
