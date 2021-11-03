package com.ufar.studentmanagementsystem.repository.impl;

import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UserRepository;
import com.ufar.studentmanagementsystem.repository.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<User> rowMapper = UserRowMapper.getUserMapper();

    @Override
    public User add(User user) {
        String sql = "INSERT INTO user(username,password,created_time) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            user.setCreatedTime(LocalDateTime.now());
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setTimestamp(3, Timestamp.valueOf(user.getCreatedTime()));
            return ps;
        }, keyHolder);
        if (inserted == 1) {
            Number key = keyHolder.getKey();
            user.setId(key.intValue());
            return user;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user WHERE enabled = true";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM user WHERE id = ? AND enabled = true";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            System.err.println("User not found with id " + id);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ? AND enabled = true";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, username);
        } catch (DataAccessException ex) {
            System.out.println("User not found with username " + username);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> update(User user) {

        String sql = "UPDATE user SET username = ?, password = ?, updated_time = ? WHERE id = ? AND enabled = true";
        int update = jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.setUpdatedTime(LocalDateTime.now()).getUpdatedTime(), user.getId());
        if (update == 1) {
            return findById(user.getId());
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "UPDATE user SET enabled = false WHERE id = ? AND enabled = true";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            System.out.println("User with id " + id + " was successfully deleted");
        }
    }
}

