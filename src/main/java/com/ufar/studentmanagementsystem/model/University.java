package com.ufar.studentmanagementsystem.model;

public class University {
    Integer universityID;
    String universityName;
    String location;
    Integer creatorId;

    public University() {
    }

    public University(Integer universityID, String universityName,
                      String location, Integer creatorId) {
        this.universityID = universityID;
        this.universityName = universityName;
        this.location = location;
        this.creatorId = creatorId;
    }

    public University(String universityName, String location, Integer creatorId) {
        this.universityName = universityName;
        this.location = location;
        this.creatorId = creatorId;
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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
