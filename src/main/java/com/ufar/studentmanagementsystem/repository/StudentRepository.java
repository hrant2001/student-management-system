package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class StudentRepository implements Repository<Integer, Student> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Student> rowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setBirthDate(rs.getDate("birth_date").toLocalDate());
        student.setFaculty(rs.getString("faculty"));
        student.setYear(rs.getInt("year"));
        student.setDegree(rs.getString("degree"));
        student.setCreatorId(rs.getInt("creator_id"));
        student.setUniversityId(rs.getInt("university_id"));

        return student;
    };

    @Transactional
    @Override
    public Student add(Student student) {
        String sql = "INSERT INTO student(first_name, last_name, birth_date, faculty, year, degree, creator_id, university_id) VALUES (?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getBirthDate()));
            ps.setString(4, student.getFaculty());
            ps.setInt(5, student.getYear());
            ps.setString(6, student.getDegree());
            ps.setInt(7, student.getCreatorId());
            ps.setInt(8, student.getUniversityId());
            return ps;
        }, keyHolder);
        if (inserted == 1) {
            Number key = keyHolder.getKey();
            student.setId(key.intValue());
            return student;
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            System.err.println("Student not found with id " + id);
        }
        return Optional.ofNullable(student);
    }

    @Transactional
    @Override
    public Optional<Student> update(Student student) {
        String sql = "UPDATE student SET first_name = ?, last_name = ?, birth_date = ?, faculty = ?, " +
                "year = ?, degree = ?, creator_id = ?, university_id = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getBirthDate(),
                student.getFaculty(), student.getYear(), student.getDegree(), student.getCreatorId(), student.getUniversityId(), student.getId());
        if (update == 1) {
            return Optional.of(student);
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM student WHERE id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            System.out.println("Student with id " + id + " was successfully deleted");
        }
    }
}
