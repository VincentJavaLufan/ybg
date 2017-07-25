package cn.sina.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import cn.sina.domain.WeiboUserVO;
import cn.sina.qvo.WeiboUserQuery;

@Repository
public class WeiboUserDaoImpl extends BaseDao implements WeiboUserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static final String	QUERY_TABLE_COLUMN	= "weibo.id,weibo.userid,weibo.uid";
	private static String		QUERY_TABLE_NAME	= "sina_user weibo";
	
	@Override
	public void create(WeiboUserVO bean) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<>();
		createmap.put("userid", bean.getUserid());
		createmap.put("uid", bean.getUid());
		baseCreate(createmap, "sina_user", "id");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, "sina_user");
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "sina_user");
	}
	
	@Override
	public List<WeiboUserVO> query(WeiboUserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new RowMapper<WeiboUserVO>() {
			
			@Override
			public WeiboUserVO mapRow(ResultSet rs, int index) throws SQLException {
				WeiboUserVO bean = new WeiboUserVO();
				bean.setId(rs.getInt("id"));
				bean.setUid(rs.getString("uid"));
				bean.setUserid(rs.getString("userid"));
				return bean;
			}
		});
	}
	
	private String getcondition(WeiboUserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		sqlappen(sql, "weibo.id", qvo.getId());
		sqlappen(sql, "weibo.userid", qvo.getUserid());
		sqlappen(sql, "weibo.uid", qvo.getUid());
		return sql.toString();
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
}
