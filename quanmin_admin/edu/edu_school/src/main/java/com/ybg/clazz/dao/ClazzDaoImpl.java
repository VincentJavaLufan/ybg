package com.ybg.clazz.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.clazz.domain.ClazzVO;
import com.ybg.clazz.mapper.ClazzMapper;
import com.ybg.clazz.qvo.ClazzQuery;

@Repository
public class ClazzDaoImpl extends BaseDao implements ClazzDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "  	 clazz.id, 	 	  clazz.classname, 	 	 clazz.classname, 	 	  clazz.schoolid, 	 	 clazz.schoolid, 	 	  clazz.gradeid, 	 	 clazz.gradeid, 	 	  clazz.gradename, 	 	 clazz.gradename, 	 	  clazz.regionid, 	 	 clazz.regionid, 	 	  clazz.schoolname, 	 	 clazz.schoolname, 	 id";
	private static String	QUERY_TABLE_COLUMN	= "edu_clazz  clazz";
	
	@Override
	public ClazzVO save(ClazzVO clazz) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("classname", clazz.getClassname());
		createmap.put("schoolid", clazz.getSchoolid());
		createmap.put("gradeid", clazz.getGradeid());
		createmap.put("gradename", clazz.getGradename());
		createmap.put("regionid", clazz.getRegionid());
		createmap.put("schoolname", clazz.getSchoolname());
		id = baseCreate(createmap, "edu_clazz", "id");
		clazz.setId((String) id);
		return clazz;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "edu_clazz");
	}
	
	@Override
	public Page list(Page page, ClazzQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new ClazzMapper()));
		}
		else {
			page.setResult(new ArrayList<ClazzVO>());
		}
		return page;
	}
	
	private String getcondition(ClazzQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("clazz.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("clazz.isdelete=0");// 默认
		// }
		sqlappen(sql, "clazz.id", qvo.getId());
		sqlappen(sql, "clazz.classname", qvo.getClassname());
		sqlappen(sql, "clazz.schoolid", qvo.getSchoolid());
		sqlappen(sql, "clazz.gradeid", qvo.getGradeid());
		sqlappen(sql, "clazz.gradename", qvo.getGradename());
		sqlappen(sql, "clazz.regionid", qvo.getRegionid());
		sqlappen(sql, "clazz.schoolname", qvo.getSchoolname());
		return sql.toString();
	}
	
	@Override
	public List<ClazzVO> list(ClazzQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new ClazzMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "edu_clazz");
	}
	
	@Override
	public ClazzVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<ClazzVO> list = getJdbcTemplate().query(sql.toString(), new ClazzMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
