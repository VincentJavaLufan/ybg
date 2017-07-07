package com.ybg.school.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.school.domain.SchoolVO;

public class SchoolMapper implements RowMapper<SchoolVO> {
	

	public SchoolVO mapRow(ResultSet rs, int index) throws SQLException {
		SchoolVO bean = new SchoolVO();
						bean.setId(rs.getString("Id"));
								bean.setSchoolname(rs.getString("Schoolname"));
								bean.setSchooltype(rs.getInt("Schooltype"));
								bean.setInfo(rs.getString("Info"));
						return bean;
	}
}
