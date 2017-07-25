package com.ybg.weixin.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.weixin.domain.WeiXinSettingDO;

@Repository
public class WeixinSettingDaoImpl extends BaseDao implements WeixinSettingDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public WeiXinSettingDO getIsUse() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,appid,secret,state,parentKey,notifyurl,mchid,isuse,redirecturi,scope from sys_wxsetting wx");
		sql.append(" where isuse=1 ");
		List<WeiXinSettingDO> list = getJdbcTemplate().query(sql.toString(), new RowMapper<WeiXinSettingDO>() {
			
			@Override
			public WeiXinSettingDO mapRow(ResultSet rs, int index) throws SQLException {
				WeiXinSettingDO bean = new WeiXinSettingDO();
				bean.setAppid(rs.getString(rs.getString("appid")));
				bean.setSecret(rs.getString("secret"));
				bean.setState(rs.getString("state"));
				bean.setPartnerkey(rs.getString("partnerkey"));
				bean.setNotify_url(rs.getString("notify_url"));
				bean.setMch_id(rs.getString("mch_id"));
				bean.setIsuse(1);
				bean.setRedirect_uri(rs.getString("redirecturi"));
				bean.setScope(rs.getString("scope"));
				return bean;
			}
		});
		return QvoConditionUtil.checkList(list) ? list.get(0) : new WeiXinSettingDO();
	}
}
