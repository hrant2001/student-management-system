package com.ufar.studentmanagementsystem.utils.rowmapper;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper {
    public static RowMapper<Student> getStudentMapper() {
        return (rs, rowNum) -> {
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
    }
}
