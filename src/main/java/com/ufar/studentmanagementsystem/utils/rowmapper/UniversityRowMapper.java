package com.ufar.studentmanagementsystem.utils.rowmapper;

import com.ufar.studentmanagementsystem.model.University;
import org.springframework.jdbc.core.RowMapper;

public class UniversityRowMapper {
    public static RowMapper<University> getUniversityMapper() {
        return (rs, rowNum) -> {
            University university = new University();
            university.setId(rs.getInt("id"))
                    .setUniversityName(rs.getString("name"))
                    .setLocation(rs.getString("location"))
                    .setCreatorId(rs.getInt("creator_id"));

            return university;
        };
    }
}
