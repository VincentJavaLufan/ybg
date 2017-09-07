package com.ybg.menu.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.menu.domain.WeixinButtonDO;

public class WeixinMenuMapper implements RowMapper<WeixinButtonDO> {
	
	public WeixinButtonDO mapRow(ResultSet rs, int index) throws SQLException {
		WeixinButtonDO bean = new WeixinButtonDO();
		bean.setId(rs.getString("id"));
		bean.setName(rs.getString("name"));
		bean.setKey(rs.getString("key"));
		bean.setUrl(rs.getString("url"));
		bean.setMedia_id(rs.getString("media_id"));
		bean.setAppid(rs.getString("appid"));
		bean.setPagepath(rs.getString("pagepath"));
		bean.setParentid(rs.getString("parentid"));
		return bean;
	}
}
