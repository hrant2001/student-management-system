package com.ufar.studentmanagementsystem.model;

public class University {
    Integer universityID;
    String universityName;
    String location;
    Integer creatorID;


    public University(Integer universityID, String universityName,
                      String location, Integer creatorID) {
        this.universityID = universityID;
        this.universityName = universityName;
        this.location = location;
        this.creatorID = creatorID;
    }

    public University(String universityName, String location, Integer creatorID) {
        this.universityName = universityName;
        this.location = location;
        this.creatorID = creatorID;
    }


    public Integer getUniversityID() {
        return universityID;
    }

    public void setUniversityID(Integer universityID) {
        this.universityID = universityID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }
}
