package com.ybg.company.dao.impl;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.company.dao.EmployeeDao;
import com.ybg.company.domain.Employee;
import com.ybg.company.mapper.EmployeeMapper;
import com.ybg.company.qvo.EmployeeQvo;
import com.ybg.component.org.inter.Organization;

@Repository
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
	
	private static final String	QUERY_TABLE_COLUMN	= "oa_employee.id,oa_employee.name,oa_employee.companyid,oa_employee.deptid";
	private static String		QUERY_TABLE_NAME	= "oa_employee oa_employee";
	
	@Override
	public Employee create(Employee bean) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("`id`", bean.getId());
		createmap.put("`name`", bean.getName());
		createmap.put("`companyid`", bean.getCompanyid());
		createmap.put("`deptid`", bean.getDeptid());
		Object id = baseCreate(createmap, "oa_employee", "id");
		bean.setId((String) id);
		return bean;
	}
	
	@Override
	public List<Employee> query(EmployeeQvo qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new EmployeeMapper());
	}
	
	private String getcondition(EmployeeQvo qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		sqlappen(sql, "oa_employee.`id`", qvo.getId());
		sqlappen(sql, "oa_employee.`name`", qvo.getName());
		sqlappen(sql, "oa_employee.`companyid`", qvo.getCompanyid());
		sqlappen(sql, "oa_employee.`deptid`", qvo.getDeptid());
		return sql.toString();
	}
	
	@Override
	public Page query(Page page, EmployeeQvo qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new EmployeeMapper()));
		page.setTotals(queryForInt(sql));
		return page;
	}
	
	@Override
	public void remove(BaseMap<String, Object> condictionmap) {
		baseremove(condictionmap, "oa_employee");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, "oa_employee");
	}
	
	@Override
	public Organization get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append(" id='" + id + "'");
		List<Employee> list = getJdbcTemplate().query(sql.toString(), new EmployeeMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
