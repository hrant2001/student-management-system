package com.ufar.studentmanagementsystem.controller;

import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.service.StudentService;
import com.ufar.studentmanagementsystem.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    private final UniversityService universityService;

    @Autowired
    public StudentController(StudentService studentService, UniversityService universityService) {
        this.studentService = studentService;
        this.universityService = universityService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student newStudent) {
        return new ResponseEntity<>(studentService.addStudent(newStudent), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/add_image")
    public ResponseEntity<Student> addImageToStudent(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {
        return new ResponseEntity<>(studentService.addImage(id, image), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findStudents() {
        List<Student> students = studentService.findStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/from/{id}")
    public String findStudentFromUniversityWithId(Model model, @PathVariable Integer id) {
        List<Student> students = studentService.findStudentsFromUniversity(id);
        University university = universityService.findUniversityById(id);

        model.addAttribute("students", students);
        model.addAttribute("university", university);

        return "students";
    }

    @GetMapping("/from/{id}/new_stud")
    public String findStudentsForAdding(Model model, @PathVariable Integer id) {
        List<Student> students = studentService.findStudentsFromUniversity(id);

        model.addAttribute("students", students);

        return "add_student";

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

    @DeleteMapping("/{id}/delete_image")
    public ResponseEntity<?> deleteImageByStudentId(@PathVariable Integer id) {
        studentService.deleteImageByStudentId(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
