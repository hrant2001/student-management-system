package com.ufar.studentmanagementsystem.repository.impl;

import com.ufar.studentmanagementsystem.model.Student;
import com.ufar.studentmanagementsystem.repository.rowmapper.StudentRowMapper;
import com.ufar.studentmanagementsystem.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryImpl.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Student> rowMapper = StudentRowMapper.getStudentMapper();

    @Override
    public Student add(Student student) {
        String sql = "INSERT INTO student(first_name, last_name, birth_date, faculty, year, degree, creator_id, university_id, created_time) VALUES (?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int inserted = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            student.setCreatedTime(LocalDateTime.now());
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getBirthDate()));
            ps.setString(4, student.getFaculty());
            ps.setInt(5, student.getYear());
            ps.setString(6, student.getDegree());
            ps.setInt(7, student.getCreatorId());
            ps.setInt(8, student.getUniversityId());
            ps.setTimestamp(9, Timestamp.valueOf(student.getCreatedTime()));
            return ps;
        }, keyHolder);
        if (inserted == 1) {
            Number key = keyHolder.getKey();
            student.setId(key.intValue());
            return student;
        }
        LOGGER.warn("The student {} is not added", student);
        return null;
    }

    @Override
    public Student addImage(Integer id, MultipartFile image) {
        Optional<Student> existingStudent = findById(id);
        String sql = "UPDATE student SET image = ? where id = ? AND enabled = true";
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int update = jdbcTemplate.update(sql, imageBytes, id);
        if (update == 1) {
            return existingStudent.get().setImage(imageBytes);
        }
        LOGGER.warn("The image is not added for student with id {}", id);
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student WHERE enabled = true";
        LOGGER.info("The students are found");
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        String sql = "SELECT * FROM student WHERE id = ? AND enabled = true";
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            LOGGER.error("Student not found with id {}", id);
        }
        LOGGER.warn("The student {} is found", id);
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> update(Student student) {

        String sql = "UPDATE student SET first_name = ?, last_name = ?, birth_date = ?, faculty = ?, year = ?, degree=?, creator_id=?, university_id=?,  updated_time = ? WHERE id = ? AND enabled = true";
        int update = jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getBirthDate(), student.getFaculty(), student.getYear(),
                student.getDegree(), student.getCreatorId(), student.getUniversityId(), student.setUpdatedTime(LocalDateTime.now()).getUpdatedTime(), student.getId());
        if (update == 1) {
            return findById(student.getId());
        }
        LOGGER.warn("The student {} is not updated", student);
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "UPDATE student SET enabled = false WHERE id = ? AND enabled = true";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            LOGGER.info("Student with id {} was successfully deleted", id);
        }
    }

    @Override
    public void deleteImageByStudentId(Integer id) {
        String sql = "UPDATE student SET image = null WHERE id = ? AND enabled = true";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            LOGGER.info("Student's image with id {} was successfully deleted", id);
        }
    }
}
