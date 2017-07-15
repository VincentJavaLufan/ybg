package com.ybg.oa.employee.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.oa.employee.domain.EmployeeVO;

public class EmployeeMapper implements RowMapper<EmployeeVO> {
	

	public EmployeeVO mapRow(ResultSet rs, int index) throws SQLException {
		EmployeeVO bean = new EmployeeVO();
						bean.setId(rs.getString("Id"));
								bean.setName(rs.getString("Name"));
								bean.setCompanyid(rs.getString("Companyid"));
								bean.setDeptid(rs.getString("Deptid"));
								bean.setUserid(rs.getString("Userid"));
								bean.setManager(rs.getInt("Manager"));
								bean.setCompanyname(rs.getString("Companyname"));
						return bean;
	}
}
