package com.ybg.rbac.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQuery;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "sys_user user";
	private static String	QUERY_TABLE_COLUMN	= " user.id, user.username, user.phone, user.email, user.state, user.password, user.createtime, user.isdelete, user.roleid, user.credentialssalt ";
	
	@Override
	public UserVO save(UserVO user) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("username", user.getUsername());
		createmap.put("phone", user.getPhone());
		createmap.put("email", user.getEmail());
		createmap.put("state", user.getState());
		createmap.put("password", user.getPassword());
		createmap.put("createtime", user.getCreatetime());
		createmap.put("roleid", user.getRoleid());
		createmap.put("credentialssalt", user.getCredentialssalt());
		String id = null;
		id = baseCreate(createmap, "sys_user", "id");
		user.setId(id);
		return user;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "sys_user");
	}
	
	@Override
	public Page list(Page page, UserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(",role.`name` rolename").append(FROM).append(QUERY_TABLE_NAME).append(LEFT).append(JOIN).append("sys_role role").append(ON).append("user.roleid=role.id");
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<UserVO>(UserVO.class)));
		}
		else {
			page.setResult(new ArrayList<UserVO>());
		}
		return page;
	}
	
	private String getcondition(UserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1 ");
		if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
			sql.append(AND).append("user.isdelete=").append(qvo.getIsdelete());
		}
		else {
			sql.append(AND).append("user.isdelete=0");// 默认
		}
		sqlappen(sql, "user.state", qvo.getState(), qvo);
		sqlappen(sql, "user.id", qvo.getId());
		sqlappen(sql, "user.createtime", qvo.getCreatetime(), qvo);
		sqlappen(sql, "user.email", qvo.getEmail(), qvo);
		sqlappen(sql, "user.password", qvo.getPassword());
		sqlappen(sql, "user.phone", qvo.getPhone(), qvo);
		sqlappen(sql, "user.username", qvo.getUsername(), qvo);
		sqlappen(sql, "user.roleid", qvo.getRoleid());
		sqlappen(sql, "user.credentialssalt", qvo.getCredentialssalt());
		return sql.toString();
	}
	
	@Override
	public List<UserVO> list(UserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(",role.`name` rolename").append(FROM).append(QUERY_TABLE_NAME).append(LEFT).append(JOIN).append("sys_role role").append(ON).append("user.roleid=role.id");
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<UserVO>(UserVO.class));
	}
	
	@Override
	public UserVO login(String loginname) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("username='").append(loginname).append("'");
		sql.append(OR).append("email='").append(loginname).append("'");
		sql.append(OR).append("phone='").append(loginname).append("'");
		List<UserVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<UserVO>(UserVO.class));
		return list.size() == 0 ? null : list.get(0);
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "sys_user");
	}
	
	@Override
	public void removeExpired() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(DELETE).append(FROM).append("sys_user").append(WHERE);
		sql.append("createtime<'").append(DateUtil.convertToStrwithformat(DateUtil.setDate(new Date(), 0, 0, 0, -3), "yyyy-MM-dd hh:mm:ss")).append("'");
		sqlappen(sql, "state", UserStateConstant.DIE);
		getJdbcTemplate().update(sql.toString());
	}
	
	@Override
	public boolean checkisExist(UserQuery qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(",role.`name` rolename").append(FROM).append(QUERY_TABLE_NAME).append(LEFT).append(JOIN).append("sys_role role").append(ON).append("user.roleid=role.id");
		sql.append(WHERE).append("1=1 ");
		boolean email = QvoConditionUtil.checkString(qvo.getEmail());
		boolean username = QvoConditionUtil.checkString(qvo.getUsername());
		boolean phone = QvoConditionUtil.checkString(qvo.getPhone());
		if (!email && !username && !phone) {
			return false;
		}
		boolean check = true;
		if (email) {
			StringBuilder sqlsub = new StringBuilder();
			sqlsub.append(sql.toString()).append(" and user.email='" + qvo.getEmail() + "'");
			List<UserVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<UserVO>(UserVO.class));
			check = check && QvoConditionUtil.checkList(list);
			if (!check) {
				return check;
			}
		}
		if (username) {
			StringBuilder sqlsub = new StringBuilder();
			sqlsub.append(sql.toString()).append(" and user.username='" + qvo.getUsername() + "'");
			List<UserVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<UserVO>(UserVO.class));
			check = check && QvoConditionUtil.checkList(list);
			if (!check) {
				return check;
			}
		}
		if (email) {
			StringBuilder sqlsub = new StringBuilder();
			sqlsub.append(sql.toString()).append(" and user.email='" + qvo.getEmail() + "'");
			List<UserVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<UserVO>(UserVO.class));
			check = check && QvoConditionUtil.checkList(list);
		}
		return check;
	}
}
