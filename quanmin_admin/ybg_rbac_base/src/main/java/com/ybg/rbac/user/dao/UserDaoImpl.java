package com.ybg.rbac.user.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "sys_user user";
	private static String	QUERY_TABLE_COLUMN	= " user.id, user.username, user.phone, user.email, user.state, user.password, user.createtime, user.isdelete, user.credentialssalt ";
	
	@Override
	public UserVO save(UserVO user) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("username", user.getUsername());
		createmap.put("phone", user.getPhone());
		createmap.put("email", user.getEmail());
		createmap.put("state", user.getState());
		createmap.put("password", user.getPassword());
		createmap.put("createtime", user.getCreatetime());
		// createmap.put("roleid", user.getRoleid());
		createmap.put("credentialssalt", user.getCredentialssalt());
		String id = null;
		id = baseCreate(createmap, "sys_user", "id");
		user.setId(id);
		// 保存 用户角色信息
		for (String roleid : user.getRoleids()) {
			BaseMap<String, Object> createrolemap = new BaseMap<>();
			createrolemap.put("roleid", roleid);
			createrolemap.put("userid", user.getId());
			saveOrUpdate(createrolemap, "sys_user_role", "", new String[] { "roleid", "userid" });
		}
		return user;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> whereMap) {
		this.baseupdate(updatemap, whereMap, "sys_user");
	}
	
	@Override
	public Page list(Page page, UserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
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
			// 默认
			sql.append(AND).append("user.isdelete=0");
		}
		sqlappen(sql, "user.state", qvo.getState(), qvo);
		sqlappen(sql, "user.id", qvo.getId());
		sqlappen(sql, "user.createtime", qvo.getCreatetime(), qvo);
		sqlappen(sql, "user.email", qvo.getEmail(), qvo);
		sqlappen(sql, "user.password", qvo.getPassword());
		sqlappen(sql, "user.phone", qvo.getPhone(), qvo);
		sqlappen(sql, "user.username", qvo.getUsername(), qvo);
		// sqlappen(sql, "user.roleid", qvo.getRoleid());
		sqlappen(sql, "user.credentialssalt", qvo.getCredentialssalt());
		return sql.toString();
	}
	
	@Override
	public List<UserVO> list(UserQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
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
		UserVO bean = list.size() == 0 ? null : list.get(0);
		// 查询角色
		if (bean != null) {
			List<String> roleids = new ArrayList<>();
			List<String> rolekeys = new ArrayList<>();
			getJdbcTemplate().query("SELECT a.roleid,b.rolekey FROM sys_user_role a , sys_role b  WHERE a.roleid=b.id  and  userid=?", new RowMapper<String>() {
				
				@Override
				public String mapRow(ResultSet rs, int index) throws SQLException {
					roleids.add(rs.getString("roleid"));
					rolekeys.add(rs.getString("rolekey"));
					return "";
				}
			}, bean.getId());
			bean.setRoleids(roleids);
			bean.setRolekeys(rolekeys);
		}
		return bean;
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
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
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
	
	@Override
	public UserVO loginById(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append(" user.Id='").append(userId).append("'");
		List<UserVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<UserVO>(UserVO.class));
		UserVO bean = list.size() == 0 ? null : list.get(0);
		// 查询角色
		if (bean != null) {
			List<String> roleids = new ArrayList<>();
			List<String> rolekeys = new ArrayList<>();
			getJdbcTemplate().query("SELECT a.roleid,b.rolekey FROM sys_user_role a , sys_role b  WHERE a.roleid=b.id  and  userid=?", new RowMapper<String>() {
				
				@Override
				public String mapRow(ResultSet rs, int index) throws SQLException {
					roleids.add(rs.getString("roleid"));
					rolekeys.add(rs.getString("rolekey"));
					return "";
				}
			}, bean.getId());
			bean.setRoleids(roleids);
			bean.setRolekeys(rolekeys);
		}
		return bean;
	}
	
	@Override
	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		List<String> localUserIds = getJdbcTemplate().queryForList("SELECT userId FROM " + "sys_" + "UserConnection where providerId = ? and providerUserId = ?", String.class, key.getProviderId(), key.getProviderUserId());
		return localUserIds;
	}
	
	@Override
	public void updateUserRole(String userid, List<String> roleids) {
		getJdbcTemplate().update(" DELETE FROM sys_user_role where userid=?", userid);
		getJdbcTemplate().batchUpdate("INSERT INTO sys_user_role (roleid,userid) values(?,?)", new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				int count = 1;
				ps.setString(count++, roleids.get(index));
				ps.setString(count++, userid);
			}
			
			@Override
			public int getBatchSize() {
				return roleids.size();
			}
		});
	}
}
