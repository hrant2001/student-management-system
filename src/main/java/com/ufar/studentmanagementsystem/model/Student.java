package com.ufar.studentmanagementsystem.model;

import java.util.Date;

public class Student {
    Integer studentID;
    Integer creatorID;
    Integer universityID;
    String firstName;
    String lastName;
    Date birthDate;
    String faculty;
    int year;
    String degree;


    public Student(Integer studentID, Integer creatorID, Integer universityID,
                   String firstName, String lastName, Date birthDate,
                   String faculty, int year, String degree) {
        this.studentID = studentID;
        this.creatorID = creatorID;
        this.universityID = universityID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.faculty = faculty;
        this.year = year;
        this.degree = degree;
    }

    public Student(Integer creatorID, Integer universityID, String firstName
            , String lastName, Date birthDate, String faculty,
                   int year, String degree) {
        this.creatorID = creatorID;
        this.universityID = universityID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.faculty = faculty;
        this.year = year;
        this.degree = degree;
    }


    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }

    public Integer getUniversityID() {
        return universityID;
    }

    public void setUniversityID(Integer universityID) {
        this.universityID = universityID;
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
