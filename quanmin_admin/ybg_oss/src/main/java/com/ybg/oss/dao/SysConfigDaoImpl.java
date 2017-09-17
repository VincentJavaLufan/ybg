package com.ybg.oss.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.BaseQueryAble;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.oss.domian.SysConfigEntity;

@Repository
public class SysConfigDaoImpl extends BaseDao implements SysConfigDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public String queryByKey(String paramKey) {
		StringBuilder sql = new StringBuilder();
		sql.append("select value from sys_config  sys_config where sys_config.key='" + paramKey + "'");
		List<SysConfigEntity> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SysConfigEntity>(SysConfigEntity.class));
		return QvoConditionUtil.checkList(list) ? list.get(0).getValue() : null;
	}
	
	@Override
	public int updateValueByKey(String key, String value) {
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		updatemap.put("`value`", value);
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		wheremap.put("`key`", key);
		return baseupdate(updatemap, wheremap, "sys_config");
	}
	
	@Override
	public void update(SysConfigEntity config) {
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		updatemap.put("`key`", config.getKey());
		updatemap.put("`value`", config.getValue());
		updatemap.put("`remark`", config.getRemark());
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		wheremap.put("id", config.getId());
		baseupdate(updatemap, wheremap, "sys_config");
	}
	
	@Override
	public void deleteBatch(Long[] ids) {
		BaseMap<String, Object> conditionmap = new BaseMap<>();
		for (Long id : ids) {
			if (id != null) {
				conditionmap.put("id", id);
				baseremove(conditionmap, "sys_config");
			}
		}
	}
	
	@Override
	public SysConfigEntity queryObject(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,key,value,status,remark from sys_config where id=" + id);
		List<SysConfigEntity> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SysConfigEntity>(SysConfigEntity.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
	
	@Override
	public void save(SysConfigEntity config) throws Exception {
		Map<String, Object> createmap = new BaseMap<>();
		createmap.put("key", config.getKey());
		createmap.put("value", config.getValue());
		createmap.put("remark", config.getRemark());
		basecreate(createmap, "sys_config", true, new Long(0));
	}
	
	@Override
	public Page list(Page page, SysConfigEntity qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,key,value,status,remark from sys_config sys_config");
		sql.append(" where 1=1 ");
		sqlappen(sql, "sys_config.key", qvo.getKey(), new BaseQueryAble() {
			
			@Override
			public boolean isBlurred() {
				return true;
			}
		});
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			List<SysConfigEntity> list = getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<SysConfigEntity>(SysConfigEntity.class));
			page.setResult(list);
		}
		else {
			page.setResult(new ArrayList<SysConfigEntity>());
		}
		return page;
	}
}
