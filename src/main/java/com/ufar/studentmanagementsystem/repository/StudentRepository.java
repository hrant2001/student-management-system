package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
        student.setStudentId(rs.getInt("student_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setBirthDate(rs.getDate("birth_date"));
        student.setFaculty(rs.getString("faculty"));
        student.setYear(rs.getInt("year"));
        student.setDegree(rs.getString("degree"));
        student.setCreatorId(rs.getInt("creator_id"));
        student.setUniversityId(rs.getInt("university_id"));

        return student;
    };

    @Override
    public List<Student> findAll() {
        String sql = "select * from student";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Student add(Student student) {
        String sql = "insert into student values(null,?,?,?,?,?,?,?,?)";
        int inserted = jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getBirthDate(),
                student.getFaculty(), student.getYear(), student.getDegree(), student.getCreatorId(), student.getUniversityId());
        if (inserted == 1) {
            // TODO studentId is null
            return student;
        }
        return null;
    }

    @Override
    public Optional<Student> findById(Integer id) {
        String sql = "SELECT * from student where student_id = ?";
        Student student = null;
        try {
            // TODO use another method because this one is deprecated
            student = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException ex) {
            System.err.println("Student not found with id " + id);
        }
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> update(Student student) {
        String sql = "update student set first_name = ?, last_name = ?, birth_date = ?, faculty = ?, " +
                "year = ?, degree = ?, creator_id = ?, university_id = ? where student_id = ?";
        int update = jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getBirthDate(),
                student.getFaculty(), student.getYear(), student.getDegree(), student.getCreatorId(), student.getUniversityId(), student.getStudentId());
        if (update == 1) {
            return Optional.of(student);
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "delete from student where student_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            System.out.println("Student with id " + id + " was successfully deleted");
        }
    }
}
