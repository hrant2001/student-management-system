package com.ufar.studentmanagementsystem.repository.impl;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
import com.ufar.studentmanagementsystem.repository.rowmapper.UniversityRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UniversityRepositoryImpl implements UniversityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityRepositoryImpl.class);
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public UniversityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<University> rowMapper = UniversityRowMapper.getUniversityMapper();

    @Override
    public University add(University university) {
        String sql = "INSERT INTO university(name,location,creator_id, created_time) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            university.setCreatedTime(LocalDateTime.now());
            ps.setString(1, university.getUniversityName());
            ps.setString(2, university.getLocation());
            ps.setInt(3, university.getCreatorId());
            ps.setTimestamp(4, Timestamp.valueOf(university.getCreatedTime()));
            return ps;
        }, keyHolder);
        if (inserted == 1) {
            Number key = keyHolder.getKey();
            university.setId(key.intValue());
            return university;
        }
        LOGGER.warn("The university {} is not added", university);
        return null;
    }

    @Override
    public List<University> findAll() {
        String sql = "SELECT * FROM university WHERE enabled = true";
        LOGGER.info("The universities are found");
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<University> findById(Integer id) {
        String sql = "SELECT * FROM university WHERE id = ? AND enabled = true";
        University university = null;
        try {
            university = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            System.err.println("University not found with id " + id);
        }
        return Optional.ofNullable(university);
    }


    @Override
    public Optional<University> update(University university) {

        String sql = "UPDATE university SET name = ?, location = ?, updated_time = ? WHERE id = ? AND enabled = true";
        int update = jdbcTemplate.update(sql, university.getUniversityName(), university.getLocation(), university.setUpdatedTime(LocalDateTime.now()).getUpdatedTime(), university.getId());
        if (update == 1) {
            return findById(university.getId());
        }
        LOGGER.warn("The university {} is not updated", university);
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "UPDATE university SET enabled = false WHERE id = ? AND enabled = true";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            LOGGER.info("University with id {} was successfully deleted", id);
        }
    }
}

