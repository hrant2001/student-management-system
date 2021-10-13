package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UniversityRepository implements Repository<Integer, University> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UniversityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<University> rowMapper = (rs, rowNum) -> {
        University university = new University();
        university.setUniversityID(rs.getInt("university_id"));
        university.setUniversityName(rs.getString("university_name"));
        university.setLocation(rs.getString("location"));
        university.setCreatorId(rs.getInt("creator_id"));

        return university;
    };

    @Override
    public List<University> findAll() {
        String sql = "select * from university";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public University add(University university) {
        String sql = "insert into university values(null,?,?,?)";
        int inserted = jdbcTemplate.update(sql, university.getUniversityName(), university.getLocation(), university.getCreatorId());
        if (inserted == 1) {
            // TODO universityId is null
            return university;
        }
        return null;
    }

    @Override
    public Optional<University> findById(Integer id) {
        String sql = "SELECT * from university where university_id = ?";
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
        String sql = "update university set university_name = ?, location = ?, creator_id = ? where university_id = ?";
        int update = jdbcTemplate.update(sql, university.getUniversityName(), university.getLocation(), university.getCreatorId(), university.getUniversityID());
        if (update == 1) {
            return Optional.of(university);
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "delete from university where university_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            System.out.println("University with id " + id + " was successfully deleted");
        }
    }
}
