package com.ufar.studentmanagementsystem.repository.impl;

import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UserRepository;
import com.ufar.studentmanagementsystem.utils.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
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

    @Transactional
    @Override
    public User add(User user) {
        String sql = "INSERT INTO user(username,password) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
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
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            System.err.println("User not found with id " + id);
        }
        return Optional.ofNullable(user);
    }

    @Transactional
    @Override
    public Optional<User> update(User user) {

        String sql = "UPDATE user SET username = ?, password = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getId());
        if (update == 1) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM user WHERE id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            System.out.println("User with id " + id + " was successfully deleted");
        }
    }
}