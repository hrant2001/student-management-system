package com.ufar.studentmanagementsystem.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class University {
    private Integer id;
    private String universityName;
    private String location;
    private Integer creatorId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime removedTime;
    private boolean enabled = true;

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

    public University(String universityName, String location, Integer creatorId,
                      LocalDateTime createdTime, LocalDateTime updatedTime, LocalDateTime removedTime) {
        this.universityName = universityName;
        this.location = location;
        this.creatorId = creatorId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.removedTime = removedTime;
    }

    public Integer getId() {
        return id;
    }

    public University setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUniversityName() {
        return universityName;
    }

    public University setUniversityName(String universityName) {
        this.universityName = universityName;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public University setLocation(String location) {
        this.location = location;
        return this;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public University setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public LocalDateTime getRemovedTime() {
        return removedTime;
    }

    public void setRemovedTime(LocalDateTime removedTime) {
        this.removedTime = removedTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
