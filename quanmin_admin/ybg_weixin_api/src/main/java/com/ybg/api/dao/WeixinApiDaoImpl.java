package com.ybg.api.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
/**
 * @author Deament
 * @date 2017年10月1日
 * **/
@Repository
public class WeixinApiDaoImpl extends BaseDao implements WeixinApiDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public Map<String, String> getSetting() {
		StringBuilder sql = new StringBuilder();
		Map<String, String> map = new LinkedHashMap<String, String>();
		sql.append(SELECT).append("`key`,`value`").append(FROM).append("weixin_login_setting_1 t");
		getJdbcTemplate().query(sql.toString(), new RowMapper<Object>() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				map.put(rs.getString("key"), rs.getString("value"));
				return null;
			}
		});
		return map;
	}
	
	@Override
	public void updateSetting(String appid, String value) {
		String tablename = "weixin_login_setting_1";
		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE).append(tablename).append(SET).append(" `value` ='").append(appid).append("'");
		sql.append(WHERE).append("`key`='appId'");
		getJdbcTemplate().update(sql.toString());
		sql = new StringBuilder();
		sql.append(UPDATE).append(tablename).append(SET).append(" `value` ='").append(value).append("'");
		sql.append(WHERE).append("`key`='secret'");
		getJdbcTemplate().update(sql.toString());
	}
}
