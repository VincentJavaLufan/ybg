package com.ybg.oa.department.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.DataBaseConstant;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.oa.department.domain.DepartmentVO;
import com.ybg.oa.department.qvo.DepartmentQuery;

@Repository
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
	
	@Autowired
	@Qualifier(DataBaseConstant.JD_OA)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_COLUMN	= " department.name,department.companyid,department.parentid,	  department.companyname, department.id ";
	private static String	QUERY_TABLE_NAME	= "oa_department  department";
	
	@Override
	public DepartmentVO save(DepartmentVO department) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("name", department.getName());
		createmap.put("companyid", department.getCompanyid());
		createmap.put("parentid", department.getParentid());
		createmap.put("companyname", department.getCompanyname());
		id = baseCreate(createmap, "oa_department", "id");
		department.setId((String) id);
		return department;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "oa_department");
	}
	
	@Override
	public Page list(Page page, DepartmentQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<DepartmentVO>(DepartmentVO.class)));
		}
		else {
			page.setResult(new ArrayList<DepartmentVO>());
		}
		return page;
	}
	
	private String getcondition(DepartmentQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("department.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("department.isdelete=0");// 默认
		// }
		sqlappen(sql, "department.id", qvo.getId());
		sqlappen(sql, "department.name", qvo.getName());
		sqlappen(sql, "department.companyid", qvo.getCompanyid());
		sqlappen(sql, "department.parentid", qvo.getParentid());
		sqlappen(sql, "department.companyname", qvo.getCompanyname());
		return sql.toString();
	}
	
	@Override
	public List<DepartmentVO> list(DepartmentQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<DepartmentVO>(DepartmentVO.class));
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "oa_department");
	}
	
	@Override
	public DepartmentVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<DepartmentVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<DepartmentVO>(DepartmentVO.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
