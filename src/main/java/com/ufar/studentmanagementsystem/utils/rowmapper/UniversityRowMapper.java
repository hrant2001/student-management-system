package com.ufar.studentmanagementsystem.utils.rowmapper;

import com.ufar.studentmanagementsystem.model.University;
import com.ufar.studentmanagementsystem.model.User;
import org.springframework.jdbc.core.RowMapper;

public class UniversityRowMapper {
    public static RowMapper<University> getUniversityMapper() {
        return (rs, rowNum) -> {
            University university = new University();
            university.setId(rs.getInt("id"));
            university.setUniversityName(rs.getString("name"));
            university.setLocation(rs.getString("location"));
            university.setCreatorId(rs.getInt("creator_id"));

            return university;
        };
    }
}
