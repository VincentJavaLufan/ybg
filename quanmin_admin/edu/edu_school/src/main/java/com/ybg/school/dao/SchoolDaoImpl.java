package com.ybg.school.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.DataBaseConstant;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.school.domain.SchoolVO;
import com.ybg.school.mapper.SchoolMapper;
import com.ybg.school.qvo.SchoolQuery;

@Repository
public class SchoolDaoImpl extends BaseDao implements SchoolDao {
	
	@Autowired
	@Qualifier(DataBaseConstant.JD_EDU)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "school.id,school.schoolname,school.schooltype,school.info,id";
	private static String	QUERY_TABLE_COLUMN	= "edu_school  school";
	
	@Override
	public SchoolVO save(SchoolVO school) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("schoolname", school.getSchoolname());
		createmap.put("schooltype", school.getSchooltype());
		createmap.put("info", school.getInfo());
		id = baseCreate(createmap, "edu_school", "id");
		school.setId((String) id);
		return school;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "edu_school");
	}
	
	@Override
	public Page list(Page page, SchoolQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new SchoolMapper()));
		}
		else {
			page.setResult(new ArrayList<SchoolVO>());
		}
		return page;
	}
	
	private String getcondition(SchoolQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("school.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("school.isdelete=0");// 默认
		// }
		sqlappen(sql, "school.id", qvo.getId());
		sqlappen(sql, "school.schoolname", qvo.getSchoolname());
		sqlappen(sql, "school.schooltype", qvo.getSchooltype());
		sqlappen(sql, "school.info", qvo.getInfo());
		return sql.toString();
	}
	
	@Override
	public List<SchoolVO> list(SchoolQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new SchoolMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "edu_school");
	}
	
	@Override
	public SchoolVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<SchoolVO> list = getJdbcTemplate().query(sql.toString(), new SchoolMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
