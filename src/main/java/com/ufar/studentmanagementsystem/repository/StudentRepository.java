package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student add(Student student);

    Student addImage(Integer id, MultipartFile image);

    List<Student> findAll();

    Optional<Student> findById(Integer id);

    Optional<Student> update(Student student);

    void deleteById(Integer id);

    void deleteImageByStudentId(Integer id);
}
