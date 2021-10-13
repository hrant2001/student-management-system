package com.ufar.studentmanagementsystem.model;

import java.util.Objects;

public class University {
    private Integer id;
    private String universityName;
    private String location;
    private Integer creatorId;

    public University() {
    }

    public University(Integer id, String universityName,
                      String location, Integer creatorId) {
        this.id = id;
        this.universityName = universityName;
        this.location = location;
        this.creatorId = creatorId;
    }

    public University(String universityName, String location, Integer creatorId) {
        this.universityName = universityName;
        this.location = location;
        this.creatorId = creatorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return id.equals(that.id) && universityName.equals(that.universityName) && location.equals(that.location) && creatorId.equals(that.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, universityName, location, creatorId);
    }

    @Override
    public String toString() {
        return "University{" +
                "universityID=" + id +
                ", universityName='" + universityName + '\'' +
                ", location='" + location + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}
