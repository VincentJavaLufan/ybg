package com.ybg.oa.department.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.oa.department.domain.DepartmentVO;

public class DepartmentMapper implements RowMapper<DepartmentVO> {
	

	public DepartmentVO mapRow(ResultSet rs, int index) throws SQLException {
		DepartmentVO bean = new DepartmentVO();
						bean.setId(rs.getString("Id"));
								bean.setName(rs.getString("Name"));
								bean.setCompanyid(rs.getString("Companyid"));
								bean.setGmtCreate(rs.getString("GmtCreate"));
								bean.setParentid(rs.getString("Parentid"));
								bean.setGmtModified(rs.getString("GmtModified"));
								bean.setCompanyname(rs.getString("Companyname"));
						return bean;
	}
}
