package com.ybg.company.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.company.domain.Employee;

public class EmployeeMapper implements RowMapper<Employee> {
	
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee bean = new Employee();
		bean.setCompanyid(rs.getString("companyid"));
		bean.setCreatetime(rs.getString("createtime"));
		bean.setId(rs.getString("id"));
		bean.setName(rs.getString("name"));
		bean.setDeptid(rs.getString("deptid"));
		return bean;
	}
}
