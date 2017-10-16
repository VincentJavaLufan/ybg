package com.ybg.rbac.role.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.rbac.role.domain.RoleResDO;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.role.qvo.RoleQuery;

@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "sys_role role";
	private static String	QUERY_TABLE_COLUMN	= " role.id,role.state,role.name,role.rolekey,role.description ,role.isdelete ";
	
	@Override
	public SysRoleVO save(SysRoleVO role) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("state", role.getState());
		createmap.put("`name`", role.getName());
		createmap.put("`rolekey`", role.getRolekey());
		createmap.put("`description`", role.getDescription());
		id = baseCreate(createmap, "sys_role", "id");
		role.setId((String) id);
		return role;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "sys_role");
	}
	
	@Override
	public Page list(Page page, RoleQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<SysRoleVO>(SysRoleVO.class)));
		}
		else {
			page.setResult(new ArrayList<SysRoleVO>());
		}
		return page;
	}
	
	private String getcondition(RoleQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
			sql.append(AND).append("role.isdelete=").append(qvo.getIsdelete());
		}
		else {
			sql.append(AND).append("role.isdelete=0");// 默认
		}
		sqlappen(sql, "role.id", qvo.getId());
		sqlappen(sql, "role.state", qvo.getState(), qvo);
		sqlappen(sql, "role.rolekey", qvo.getRolekey(), qvo);
		sqlappen(sql, "role.description", qvo.getDescription(), qvo);
		sqlappen(sql, "role.`name`", qvo.getName(), qvo);
		return sql.toString();
	}
	
	@Override
	public List<SysRoleVO> list(RoleQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SysRoleVO>(SysRoleVO.class));
	}
	
	@Override
	public void saveOrupdateRole_Res(final List<RoleResDO> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT).append(INTO).append("sys_res_role (resid,roleid,state) ").append(VALUES).append("(?,?,?) ").append(ON).append(DUPLICATE).append(KEY).append(UPDATE).append("resid=?,roleid=?, state=? ");
		getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				int count = 1;
				ps.setString(count++, list.get(index).getResid());
				ps.setString(count++, list.get(index).getRoleid());
				ps.setInt(count++, list.get(index).getState());
				ps.setString(count++, list.get(index).getResid());
				ps.setString(count++, list.get(index).getRoleid());
				ps.setInt(count++, list.get(index).getState());
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}
}
