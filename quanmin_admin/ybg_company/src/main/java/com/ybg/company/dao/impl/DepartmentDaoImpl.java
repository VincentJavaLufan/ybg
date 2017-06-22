package com.ybg.company.dao.impl;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.company.dao.DepartmentDao;
import com.ybg.company.domain.Department;
import com.ybg.company.mapper.DepartmentMapper;
import com.ybg.company.qvo.DepartmentQvo;
import com.ybg.component.org.inter.Organization;

@Repository
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
	
	private static final String	QUERY_TABLE_COLUMN	= "oa_department.id,oa_department.name,oa_department.companyid,oa_department.createtime,oa_department.parentid";
	private static String		QUERY_TABLE_NAME	= "oa_department oa_department";
	
	@Override
	public Department create(Department bean) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("`name`", bean.getName());
		createmap.put("`companyid`", bean.getCompanyid());
		createmap.put("`createtime`", bean.getCreatetime());
		createmap.put("`parentid`", bean.getParentid());
		Object id = baseCreate(createmap, "oa_department", "id");
		bean.setId((String) id);
		return bean;
	}
	
	@Override
	public List<Department> query(DepartmentQvo qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new DepartmentMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> condictionmap) {
		baseremove(condictionmap, "oa_department");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, "oa_department");
	}
	
	private String getcondition(DepartmentQvo qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		sqlappen(sql, "oa_department.`id`", qvo.getId());
		sqlappen(sql, "oa_department.`name`", qvo.getName());
		sqlappen(sql, "oa_department.`companyid`", qvo.getCompanyid());
		sqlappen(sql, "oa_department.`createtime`", qvo.getCreatetime());
		sqlappen(sql, "oa_department.`parentid`", qvo.getParentid());
		return sql.toString();
	}
	
	@Override
	public Organization get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append(" id='" + id + "'");
		List<Department> list = getJdbcTemplate().query(sql.toString(), new DepartmentMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
