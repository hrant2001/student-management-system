package com.ufar.studentmanagementsystem.controller;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping
    public ResponseEntity<University> addUniversity(@RequestBody University newUniversity) {
        University university = universityService.addUniversity(newUniversity);
        return new ResponseEntity<>(university, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<University>> findUniversities() {
        List<University> universities = universityService.findUniversities();
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> findUniversityById(@PathVariable Integer id) {
        University university = universityService.findUniversityById(id).orElse(null);
        if (university == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(university, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<University> updateUniversity(@RequestBody University updatedUniversity) {

        University university = universityService.updateUniversity(updatedUniversity).orElse(null);
        if (university == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(university, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUniversityById(@PathVariable Integer id) {
        universityService.deleteUniversityById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
