package com.qq.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.qq.domain.QQuserVO;
import com.qq.qvo.QQuserQuery;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;

@Repository
public class QQuserDaoImpl extends BaseDao implements QQuserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static final String	QUERY_TABLE_COLUMN	= "qq.id,qq.userid,qq.openid";
	private static String		QUERY_TABLE_NAME	= "qq_user qq";
	
	@Override
	public void create(QQuserVO bean) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("openid", bean.getOpenid());
		createmap.put("userid", bean.getUserid());
		baseCreate(createmap, "qq_user", "id");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, "qq_user");
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "qq_user");
	}
	
	@Override
	public List<QQuserVO> query(QQuserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<QQuserVO>());
	}
	
	private String getcondition(QQuserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		sqlappen(sql, "qq.id", qvo.getId());
		sqlappen(sql, "qq.openid", qvo.getOpenid());
		sqlappen(sql, "qq.userid", qvo.getUserid());
		return sql.toString();
	}
}
