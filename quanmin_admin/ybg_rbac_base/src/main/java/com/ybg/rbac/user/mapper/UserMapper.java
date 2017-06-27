package com.ybg.rbac.user.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.rbac.user.domain.UserVO;

public class UserMapper implements RowMapper<UserVO> {
	
	public UserVO mapRow(ResultSet rs, int index) throws SQLException {
		UserVO user = new UserVO();
		user.setId(rs.getString("id"));
		user.setCreatetime(rs.getString("createtime"));
		user.setEmail(rs.getString("email"));
		user.setIsdelete(rs.getInt("isdelete"));
		user.setPassword(rs.getString("password"));
		user.setPhone(rs.getString("phone"));
		user.setState(rs.getString("state"));
		user.setUsername(rs.getString("username"));
		user.setRoleid(rs.getString("roleid"));
		user.setCredentialssalt(rs.getString("credentialssalt"));
		user.setRolename(rs.getString("rolename"));
		return user;
	}
}
