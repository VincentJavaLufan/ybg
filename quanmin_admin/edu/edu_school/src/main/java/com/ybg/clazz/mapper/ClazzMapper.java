package com.ybg.clazz.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.clazz.domain.ClazzVO;

public class ClazzMapper implements RowMapper<ClazzVO> {
	

	public ClazzVO mapRow(ResultSet rs, int index) throws SQLException {
		ClazzVO bean = new ClazzVO();
						bean.setId(rs.getString("Id"));
								bean.setClassname(rs.getString("Classname"));
								bean.setSchoolid(rs.getString("Schoolid"));
								bean.setGradeid(rs.getInt("Gradeid"));
								bean.setGradename(rs.getString("Gradename"));
								bean.setRegionid(rs.getInt("Regionid"));
								bean.setSchoolname(rs.getString("Schoolname"));
						return bean;
	}
}
