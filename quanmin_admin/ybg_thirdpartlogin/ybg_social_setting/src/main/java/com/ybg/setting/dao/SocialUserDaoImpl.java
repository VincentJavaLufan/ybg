package com.ybg.setting.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.setting.domain.SocialUserVO;
import com.ybg.setting.qvo.SocialUserQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Repository
public class SocialUserDaoImpl extends BaseDao implements SocialUserDao {
	
	@Autowired
	JdbcTemplate				jdbcTemplate;
	private static final String	QUERY_CLOMN	= " t.userid,t.providerid,t.provideruserid,t.rank,t.displayname,t.profileurl,t.imageurl,t.accesstoken,t.secret,t.refreshtoken,t.expiretime ";
	private static final String	QUERY_TABLE	= " sys_userconnection t ";
	
	@Override
	public List<SocialUserVO> query(SocialUserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_CLOMN).append(FROM).append(QUERY_TABLE);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SocialUserVO>(SocialUserVO.class));
	}
	
	private String getcondition(SocialUserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append(" 1=1 ");
		sqlappen(sql, "t.userid", qvo.getUserid());
		sqlappen(sql, "t.providerid", qvo.getProviderid());
		sqlappen(sql, "t.provideruserid", qvo.getProviderid());
		return sql.toString();
	}
	
	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		baseremove(conditionmap, "sys_userconnection");
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
