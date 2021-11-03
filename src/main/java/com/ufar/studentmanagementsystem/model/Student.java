package com.ufar.studentmanagementsystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Student {
    private Integer id;
    private Integer creatorId;
    private Integer universityId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String faculty;
    private int year;
    private String degree;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private boolean enabled = true;

    public Student() {
    }

    public Student(Integer id, Integer creatorId, Integer universityId,
                   String firstName, String lastName, LocalDate birthDate,
                   String faculty, int year, String degree) {
        this.id = id;
        this.creatorId = creatorId;
        this.universityId = universityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.faculty = faculty;
        this.year = year;
        this.degree = degree;
    }

    public Student(Integer creatorId, Integer universityId, String firstName
            , String lastName, LocalDate birthDate, String faculty,
                   int year, String degree) {
        this.creatorId = creatorId;
        this.universityId = universityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.faculty = faculty;
        this.year = year;
        this.degree = degree;
    }

    public Integer getId() {
        return id;
    }

    public Student setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public Student setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public Student setUniversityId(Integer universityId) {
        this.universityId = universityId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Student setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getFaculty() {
        return faculty;
    }

    public Student setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Student setYear(int year) {
        this.year = year;
        return this;
    }

    public String getDegree() {
        return degree;
    }

    public Student setDegree(String degree) {
        this.degree = degree;
        return this;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Student setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public Student setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Student setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return year == student.year && id.equals(student.id) && creatorId.equals(student.creatorId) && universityId.equals(student.universityId) && firstName.equals(student.firstName) && lastName.equals(student.lastName) && birthDate.equals(student.birthDate) && faculty.equals(student.faculty) && degree.equals(student.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creatorId, universityId, firstName, lastName, birthDate, faculty, year, degree);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + id +
                ", creatorId=" + creatorId +
                ", universityId=" + universityId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", faculty='" + faculty + '\'' +
                ", year=" + year +
                ", degree='" + degree + '\'' +
                '}';
    }
}
