package com.ybg.weixin.login.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.weixin.login.domain.WeixinUserVO;
import com.ybg.weixin.login.qvo.WeixinUserQuery;

@Repository
public class WeixinUserDaoImpl extends BaseDao implements WeixinUserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static final String	QUERY_TABLE_COLUMN	= "wx.id,wx.userid,wx.openid";
	private static String		QUERY_TABLE_NAME	= "weixin_user wx";
	
	@Override
	public void create(WeixinUserVO bean) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("openid", bean.getOpenid());
		createmap.put("userid", bean.getUserid());
		baseCreate(createmap, "weixin_user", "id");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, "weixin_user");
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "weixin_user");
	}
	
	@Override
	public List<WeixinUserVO> query(WeixinUserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<WeixinUserVO>());
	}
	
	private String getcondition(WeixinUserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		sqlappen(sql, "wx.id", qvo.getId());
		if (QvoConditionUtil.checkString(qvo.getOpenid())) {
			sql.append(AND).append("wx.openid='").append(qvo.getOpenid()).append("'");
		}
		sqlappen(sql, "wx.userid", qvo.getUserid());
		return sql.toString();
	}
}
