package com.ybg.rbac.resources.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.rbac.resources.domain.SysResourcesVO;

public class ResourcesMapper implements RowMapper<SysResourcesVO> {
	
	public SysResourcesVO mapRow(ResultSet rs, int index) throws SQLException {
		SysResourcesVO bean = new SysResourcesVO();
		bean.setId(rs.getString("id"));
		bean.setName(rs.getString("name"));
		bean.setParentid(rs.getString("parentid"));
		bean.setReskey(rs.getString("reskey"));
		bean.setType(rs.getString("type"));
		bean.setResurl(rs.getString("resurl"));
		bean.setLevel(rs.getInt("level"));
		bean.setIcon(rs.getString("icon"));
		bean.setIshide(rs.getInt("ishide"));
		bean.setDescription(rs.getString("description"));
		bean.setColorid(rs.getInt("colorid"));
		bean.setColorclass(rs.getString("colorclass"));
		return bean;
	}
}
