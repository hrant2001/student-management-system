package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<Integer, User> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
/*
    TODO change to User setters and getters
    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setCourseId(rs.getInt("course_id"));
        user.setTitle(rs.getString("title"));
        user.setDescription(rs.getString("description"));
        user.setLink(rs.getString("link"));
        return user;
    };
*/
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User add(User user) {

        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {

        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
