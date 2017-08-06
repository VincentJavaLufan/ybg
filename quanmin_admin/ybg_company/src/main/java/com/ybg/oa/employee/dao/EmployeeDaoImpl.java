package com.ybg.oa.employee.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.DataBaseConstant;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.oa.employee.domain.EmployeeDO;
import com.ybg.oa.employee.domain.EmployeeVO;
import com.ybg.oa.employee.mapper.EmployeeMapper;
import com.ybg.oa.employee.qvo.EmployeeQuery;

@Repository
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
	
	@Autowired
	@Qualifier(DataBaseConstant.JD_OA)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "  	  employee.name, 	  employee.companyid, 	  employee.deptid, 	  employee.userid, 	  employee.manager, 	  employee.companyname, id";
	private static String	QUERY_TABLE_COLUMN	= "oa_employee  employee";
	
	@Override
	public EmployeeVO save(EmployeeVO employee) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("name", employee.getName());
		createmap.put("companyid", employee.getCompanyid());
		createmap.put("deptid", employee.getDeptid());
		createmap.put("userid", employee.getUserid());
		createmap.put("manager", employee.getManager());
		createmap.put("companyname", employee.getCompanyname());
		id = baseCreate(createmap, "oa_employee", "id");
		employee.setId((String) id);
		return employee;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "oa_employee");
	}
	
	@Override
	public Page list(Page page, EmployeeQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new EmployeeMapper()));
		}
		else {
			page.setResult(new ArrayList<EmployeeVO>());
		}
		return page;
	}
	
	private String getcondition(EmployeeQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("employee.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("employee.isdelete=0");// 默认
		// }
		sqlappen(sql, "employee.id", qvo.getId());
		sqlappen(sql, "employee.name", qvo.getName());
		sqlappen(sql, "employee.companyid", qvo.getCompanyid());
		sqlappen(sql, "employee.deptid", qvo.getDeptid());
		sqlappen(sql, "employee.userid", qvo.getUserid());
		sqlappen(sql, "employee.manager", qvo.getManager());
		sqlappen(sql, "employee.companyname", qvo.getCompanyname());
		return sql.toString();
	}
	
	@Override
	public List<EmployeeVO> list(EmployeeQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new EmployeeMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "oa_employee");
	}
	
	@Override
	public EmployeeVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<EmployeeVO> list = getJdbcTemplate().query(sql.toString(), new EmployeeMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
