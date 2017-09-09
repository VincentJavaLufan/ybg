package com.ybg.menu.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.menu.domain.WeixinButtonVO;

public class WeixinMenuMapper implements RowMapper<WeixinButtonVO> {
	
	public WeixinButtonVO mapRow(ResultSet rs, int index) throws SQLException {
		WeixinButtonVO bean = new WeixinButtonVO();
		bean.setId(rs.getString("id"));
		bean.setName(rs.getString("name"));
		bean.setKey(rs.getString("key"));
		bean.setUrl(rs.getString("url"));
		bean.setMedia_id(rs.getString("media_id"));
		bean.setAppid(rs.getString("appid"));
		bean.setPagepath(rs.getString("pagepath"));
		bean.setParentid(rs.getString("parentid"));
		bean.setType(rs.getString("type"));
		bean.setIfsub(rs.getInt("ifsub"));
		bean.setMenuorder(rs.getInt("menuorder"));
		bean.setButtonorder(rs.getInt("buttonorder"));
		return bean;
	}
}
