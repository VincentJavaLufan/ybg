package com.ybg.menu.dao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.menu.domain.WeixinButtonDO;
import com.ybg.menu.mapper.WeixinMenuMapper;

@Repository
public class WeixinButtonDaoImol extends BaseDao implements WeixinButtonDao {
	
	private static String	QUERY_TABLE_NAME	= "weixinMenu.name,weixinMenu.key,weixinMenu.url,weixinMenu.media_id,weixinMenu.appid,weixinMenu.pagepath,weixinMenu.parentid,weixinMenu.gmt_create,weixinMenu.gmt_modified, id";
	private static String	QUERY_TABLE_COLUMN	= "weixin_menu  weixinMenu";
	@Autowired
	JdbcTemplate			jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public void create(WeixinButtonDO weixinMenu) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("name", weixinMenu.getName());
		createmap.put("key", weixinMenu.getKey());
		createmap.put("url", weixinMenu.getUrl());
		createmap.put("media_id", weixinMenu.getMedia_id());
		createmap.put("appid", weixinMenu.getAppid());
		createmap.put("pagepath", weixinMenu.getPagepath());
		createmap.put("parentid", weixinMenu.getParentid());
		baseCreate(createmap, "weixin_menu", "id");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, "weixin_menu");
	}
	
	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		baseremove(conditionmap, "weixin_menu");
	}
	
	@Override
	public List<WeixinButtonDO> list() {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		return getJdbcTemplate().query(sql.toString(), new WeixinMenuMapper());
	}
	
	@Override
	public WeixinButtonDO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<WeixinButtonDO> list = getJdbcTemplate().query(sql.toString(), new WeixinMenuMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
