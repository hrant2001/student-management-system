package com.ufar.studentmanagementsystem.controller;

import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student newStudent) {
        return new ResponseEntity<>(studentService.addStudent(newStudent), HttpStatus.CREATED);
    }

    // TODO Modify
    @PutMapping("/{id}/add_image")
    public ResponseEntity<Student> addImageToStudent(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {
        return new ResponseEntity<>(studentService.addImage(id, image), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findStudents() {
        List<Student> students = studentService.findStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.findStudentById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student updatedStudent) {
        return new ResponseEntity<>(studentService.updateStudent(updatedStudent), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // TODO Modify
    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteImageByStudentId(@PathVariable Integer id) {
        studentService.deleteImageByStudentId(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
