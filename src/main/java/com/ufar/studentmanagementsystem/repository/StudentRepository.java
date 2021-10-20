package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository {
    Student add(Student student);

    List<Student> findAll();

    Optional<Student> findById(Integer id);

    Optional<Student> update(Student student);

    void deleteById(Integer id);
}
