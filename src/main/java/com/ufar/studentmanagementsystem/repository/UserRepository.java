package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<Integer, User> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public User add(User user) {
        String sql = "insert into user values(null,?,?)";
        int inserted = jdbcTemplate.update(sql, user.getUserName(), user.getPassword());
        if (inserted == 1) {
            // TODO userId is null
            return user;
        }
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * from user where user_id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            System.err.println("User not found with id " + id);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> update(User user) {

        String sql = "update user set username = ?, password = ? where user_id = ?";
        int update = jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getUserId());
        if (update == 1) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "delete from user where user_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            System.out.println("User with id " + id + " was successfully deleted");
        }
    }
}
