package com.ybg.company.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.company.domain.Department;

public class DepartmentMapper implements RowMapper<Department> {
	
	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department bean = new Department();
		bean.setId("id");
		bean.setName(rs.getString("name"));
		bean.setCompanyid(rs.getString("comanyid"));
		bean.setParentid(rs.getString("parentid"));
		bean.setCreatetime(rs.getString("createtime"));
		return bean;
	}
}
