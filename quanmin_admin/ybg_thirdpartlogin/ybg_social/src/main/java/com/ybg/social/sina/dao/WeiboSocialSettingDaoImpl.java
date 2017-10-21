package com.ybg.social.sina.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
@Repository
public class WeiboSocialSettingDaoImpl extends BaseDao implements WeiboSocialSettingDao {
	
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
		sql.append(SELECT).append("`key`,`value`").append(FROM).append("sina_login_setting_1 t");
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
	public void updateSetting(String appid, String value, String url) {
		String tablename = "sina_login_setting_1";
		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE).append(tablename).append(SET).append(" `value` ='").append(appid).append("'");
		sql.append(WHERE).append("`key`='client_ID'");
		getJdbcTemplate().update(sql.toString());
		sql = new StringBuilder();
		sql.append(UPDATE).append(tablename).append(SET).append(" `value` ='").append(value).append("'");
		sql.append(WHERE).append("`key`='client_SERCRET'");
		getJdbcTemplate().update(sql.toString());
		sql = new StringBuilder();
		sql.append(UPDATE).append(tablename).append(SET).append(" `value` ='").append(url).append("'");
		sql.append(WHERE).append("`key`='redirect_URI'");
		getJdbcTemplate().update(sql.toString());
	}
}
