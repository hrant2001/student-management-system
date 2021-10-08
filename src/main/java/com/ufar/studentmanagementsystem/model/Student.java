package com.ufar.studentmanagementsystem.model;

import java.util.Date;

public class Student {
    Integer studentId;
    Integer creatorId;
    Integer universityId;
    String firstName;
    String lastName;
    Date birthDate;
    String faculty;
    int year;
    String degree;

    public Student() {
    }

    public Student(Integer studentId, Integer creatorId, Integer universityId,
                   String firstName, String lastName, Date birthDate,
                   String faculty, int year, String degree) {
        this.studentId = studentId;
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
            , String lastName, Date birthDate, String faculty,
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
