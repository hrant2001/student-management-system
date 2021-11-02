package com.ufar.studentmanagementsystem.repository.rowmapper;

import com.ufar.studentmanagementsystem.model.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper {
    public static RowMapper<User> getUserMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"))
                    .setUserName(rs.getString("username"))
                    .setPassword(rs.getString("password"))
                    .setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
            if (rs.getTimestamp("updated_time") != null)
                user.setUpdatedTime(rs.getTimestamp("updated_time").toLocalDateTime());
            if (rs.getTimestamp("removed_time") != null)
                user.setRemovedTime(rs.getTimestamp("removed_time").toLocalDateTime());

            return user;
        };
    }
}
