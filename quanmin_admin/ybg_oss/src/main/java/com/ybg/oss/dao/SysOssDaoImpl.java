package com.ybg.oss.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.oss.domian.SysOssEntity;

@Repository
public class SysOssDaoImpl extends BaseDao implements SysOssDao {
	
	@Override
	public SysOssEntity queryObject(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("	select id,url,create_date from sys_oss oss where id = " + id);
		List<SysOssEntity> list = getJdbcTemplate().query(sql.toString(), new RowMapper<SysOssEntity>() {
			
			@Override
			public SysOssEntity mapRow(ResultSet rs, int index) throws SQLException {
				SysOssEntity bean = new SysOssEntity();
				bean.setCreatedate(rs.getString("createDate"));
				bean.setId(rs.getLong("id"));
				bean.setUrl(rs.getString("url"));
				return bean;
			}
		});
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
	
	@Override
	public void save(SysOssEntity sysOss) throws Exception {
		Map<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("url", sysOss.getUrl());
		createmap.put("createdate", sysOss.getCreatedate());
		basecreate(createmap, "sys_oss", true, new Long(0L));
	}
	
	@Override
	public void update(SysOssEntity sysOss) {
		BaseMap<String, Object> wheremap = new BaseMap<>();
		BaseMap<String, Object> updatemap = new BaseMap<>();
		updatemap.put("url", sysOss.getUrl());
		updatemap.put("createdate", sysOss.getCreatedate());
		wheremap.put("id", sysOss.getId());
		baseupdate(updatemap, wheremap, "sys_oss");
	}
	
	@Override
	public void delete(Long id) {
		BaseMap<String, Object> conditionmap = new BaseMap<>();
		conditionmap.put("id", id);
		if (id != null) {
			baseremove(conditionmap, "sys_oss");
		}
	}
	
	@Override
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			if (id != null) {
				BaseMap<String, Object> conditionmap = new BaseMap<>();
				conditionmap.put("id", id);
				baseremove(conditionmap, "sys_oss");
			}
		}
	}
	
	@Override
	public Page list(Page page, SysOssEntity qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("	select id,url,create_date from sys_oss oss ");
		sql.append(" where 1=1 ");
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			List<SysOssEntity> list = getJdbcTemplate().query(page.getPagesql(sql), new RowMapper<SysOssEntity>() {
				
				@Override
				public SysOssEntity mapRow(ResultSet rs, int index) throws SQLException {
					SysOssEntity bean = new SysOssEntity();
					bean.setCreatedate(rs.getString("createDate"));
					bean.setId(rs.getLong("id"));
					bean.setUrl(rs.getString("url"));
					return bean;
				}
			});
			page.setResult(list);
		}
		else {
			page.setResult(new ArrayList<SysOssEntity>());
		}
		return page;
	}
}
