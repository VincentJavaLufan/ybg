package com.ybg.rbac.role.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.rbac.role.domain.SysRole;

public class RoleMapper implements RowMapper<SysRole> {
	

	public SysRole mapRow(ResultSet rs, int index) throws SQLException {
		SysRole bean = new SysRole();
		bean.setId(rs.getString("id"));
		bean.setState(rs.getString("state"));
		bean.setName(rs.getString("name"));
		bean.setRolekey(rs.getString("rolekey"));
		bean.setDescription(rs.getString("description"));
		bean.setIsdelete(rs.getInt("isdelete"));
		return bean;
	}
}
