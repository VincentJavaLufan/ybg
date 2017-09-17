package com.ybg.mayun.oauth.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.mayun.oauth.domain.MayunUserVO;
import com.ybg.mayun.oauth.qvo.MayunUserQuery;

@Repository
public class MayunUserDaoImpl extends BaseDao implements MayunUserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static final String	QUERY_TABLE_COLUMN	= "my.id,my.userid,my.mayunid";
	private static final String	QUERY_TABLE_NAME	= "mayun_user my";
	private static final String	UPDATE_TABLE_NAME	= "mayun_user";
	
	@Override
	public void create(MayunUserVO bean) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("mayunid", bean.getMayunid());
		createmap.put("userid", bean.getUserid());
		baseCreate(createmap, UPDATE_TABLE_NAME, "id");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, UPDATE_TABLE_NAME);
	}
	
	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		baseremove(conditionmap, UPDATE_TABLE_NAME);
	}
	
	@Override
	public List<MayunUserVO> query(MayunUserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(MayunUserVO.class));
	}
	
	@Override
	public Map<String, String> getSetting() {
		StringBuilder sql = new StringBuilder();
		Map<String, String> map = new LinkedHashMap<String, String>();
		sql.append(SELECT).append("`key`,`value`").append(FROM).append("mayun_login_setting_1 t");
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
		String tablename = "mayun_login_setting_1";
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
