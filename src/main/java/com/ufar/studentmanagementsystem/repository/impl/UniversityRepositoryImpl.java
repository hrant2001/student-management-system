package com.ufar.studentmanagementsystem.repository.impl;

import com.ufar.studentmanagementsystem.model.University;
<<<<<<< HEAD:src/main/java/com/ufar/studentmanagementsystem/repository/UniversityRepositoryImpl.java
import com.ufar.studentmanagementsystem.utils.rowmapper.UniversityRowMapper;
=======
import com.ufar.studentmanagementsystem.repository.UniversityRepository;
>>>>>>> 3aba81fcc366359855970f9ebebc6f74951e342b:src/main/java/com/ufar/studentmanagementsystem/repository/impl/UniversityRepositoryImpl.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UniversityRepositoryImpl implements UniversityRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UniversityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<University> rowMapper = UniversityRowMapper.getUniversityMapper();

    @Override
    public University add(University university) {
        String sql = "INSERT INTO university(name,location,creator_id) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, university.getUniversityName());
            ps.setString(2, university.getLocation());
            ps.setInt(3, university.getCreatorId());
            return ps;
        }, keyHolder);
        if (inserted == 1) {
            Number key = keyHolder.getKey();
            university.setId(key.intValue());
            return university;
        }
        return null;
    }

    @Override
    public List<University> findAll() {
        String sql = "SELECT * FROM university";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<University> findById(Integer id) {
        String sql = "SELECT * FROM university WHERE id = ?";
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
        String sql = "UPDATE university SET name = ?, location = ?, creator_id = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, university.getUniversityName(), university.getLocation(), university.getCreatorId(), university.getId());
        if (update == 1) {
            return Optional.of(university);
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM university WHERE id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            System.out.println("University with id " + id + " was successfully deleted");
        }
    }
}
