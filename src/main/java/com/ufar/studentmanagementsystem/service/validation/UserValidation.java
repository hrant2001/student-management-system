package com.ufar.studentmanagementsystem.service.validation;

import com.ufar.studentmanagementsystem.model.User;

public class UserValidation {
    public static boolean IsValid(User user) {
        return user != null && user.getUserName() != null && !user.getUserName().isEmpty() && user.getPassword() != null && !user.getPassword().isEmpty();
    }
}
