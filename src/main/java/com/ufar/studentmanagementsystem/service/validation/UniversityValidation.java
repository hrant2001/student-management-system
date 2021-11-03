package com.ufar.studentmanagementsystem.service.validation;

import com.ufar.studentmanagementsystem.model.University;

public class UniversityValidation {
    public static boolean isValid(University university) {
        return university != null
                && university.getUniversityName() != null && !university.getUniversityName().isEmpty()
                && university.getLocation() != null && !university.getLocation().isEmpty()
                && university.getCreatorId() != null && university.getCreatorId() > 0
                && university.getCreatedTime() != null;
    }
}
