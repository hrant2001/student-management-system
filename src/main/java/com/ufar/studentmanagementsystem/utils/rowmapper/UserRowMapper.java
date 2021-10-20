package com.ufar.studentmanagementsystem.utils.rowmapper;

import com.ufar.studentmanagementsystem.model.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper {
    public static RowMapper<User> getUserMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));

            return user;
        };
    }
}
