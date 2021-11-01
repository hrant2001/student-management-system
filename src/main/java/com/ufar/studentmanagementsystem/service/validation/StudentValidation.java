package com.ufar.studentmanagementsystem.service.validation;

import com.ufar.studentmanagementsystem.model.Student;

public class StudentValidation {
    public static boolean IsValid(Student student) {
        return student != null && student.getCreatorId() != null && !(student.getCreatorId()<=0) && student.getUniversityId()!=null&&
               !(student.getUniversityId()<=0) && student.getFirstName() !=null && !student.getFirstName().isEmpty() &&
                student.getLastName() !=null && !student.getLastName().isEmpty() && student.getBirthDate() != null &&
                !(student.getYear() <= 0) && student.getDegree() !=null && student.getDegree().isEmpty();
    }
}
