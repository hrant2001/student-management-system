package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.add(student);
    }

    public Optional<Student> findStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> updateStudent(Student student) {
        return studentRepository.update(student);
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }
}
