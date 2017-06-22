package com.ybg.company.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.company.domain.Company;

public class CompanyMapper implements RowMapper<Company> {
	
	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company bean = new Company();
		bean.setId(rs.getString("id"));
		bean.setCreatetime(rs.getString("createtime"));
		bean.setName(rs.getString("name"));
		bean.setQualification(Boolean.parseBoolean(rs.getString("isauth")));
		return bean;
	}
}
