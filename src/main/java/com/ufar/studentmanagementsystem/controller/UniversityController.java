package com.ufar.studentmanagementsystem.controller;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping
    public ResponseEntity<University> addUniversity(@RequestBody University newUniversity) {
        return new ResponseEntity<>(universityService.addUniversity(newUniversity), HttpStatus.CREATED);
    }

    @GetMapping
    public String findUniversities(Model model) {
        List<University> universities = universityService.findUniversities();
        model.addAttribute("universities", universities);
        return "universities";

        //return new ResponseEntity<>(university, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> findUniversityById(@PathVariable Integer id) {
        return new ResponseEntity<>(universityService.findUniversityById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<University> updateUniversity(@RequestBody University updatedUniversity) {
        return new ResponseEntity<>(universityService.updateUniversity(updatedUniversity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUniversityById(@PathVariable Integer id) {
        universityService.deleteUniversityById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
