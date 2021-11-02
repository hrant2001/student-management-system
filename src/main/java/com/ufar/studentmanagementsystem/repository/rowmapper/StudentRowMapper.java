package com.ufar.studentmanagementsystem.repository.rowmapper;

import com.ufar.studentmanagementsystem.model.Student;
import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper {
    public static RowMapper<Student> getStudentMapper() {
        return (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    .setBirthDate(rs.getDate("birth_date").toLocalDate())
                    .setFaculty(rs.getString("faculty"))
                    .setYear(rs.getInt("year"))
                    .setDegree(rs.getString("degree"))
                    .setCreatorId(rs.getInt("creator_id"))
                    .setUniversityId(rs.getInt("university_id"))
                    .setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
            if (rs.getTimestamp("updated_time") != null)
                student.setUpdatedTime(rs.getTimestamp("updated_time").toLocalDateTime());
            if (rs.getTimestamp("removed_time") != null)
                student.setRemovedTime(rs.getTimestamp("removed_time").toLocalDateTime());

            return student;
        };
    }
}
