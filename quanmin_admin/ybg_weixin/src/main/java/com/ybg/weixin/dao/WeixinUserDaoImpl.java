package com.ybg.weixin.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.weixin.domain.WXuserDO;

@Repository
public class WeixinUserDaoImpl extends BaseDao implements WeixinUserDao {
	
	@Override
	public WXuserDO getByopenId(String openID) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select userid,openid from weixin_user u where openid=?");
		List<WXuserDO> list = getJdbcTemplate().query(sql.toString(), new RowMapper<WXuserDO>() {
			
			@Override
			public WXuserDO mapRow(ResultSet rs, int index) throws SQLException {
				WXuserDO bean = new WXuserDO();
				bean.setOpenid(rs.getString("openid"));
				bean.setUserid(rs.getString("userid"));
				return bean;
			}
		},openID);
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
	
	@Override
	public void create(WXuserDO bean) throws Exception {
		BaseMap<String, Object> createmap= new BaseMap<String, Object>();
		createmap.put("userid", bean.getUserid());
		createmap.put("userid", bean.getUserid());
		baseCreate(createmap, "weixin_user", "id");
	}
}
