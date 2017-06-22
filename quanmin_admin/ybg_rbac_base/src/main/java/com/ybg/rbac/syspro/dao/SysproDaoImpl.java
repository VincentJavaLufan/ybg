package com.ybg.rbac.syspro.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.rbac.syspro.domain.Syspro;
import com.ybg.rbac.syspro.qvo.SysproQvo;

@Repository
public class SysproDaoImpl extends BaseDao implements SysproDao {
	
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "sys_properties");
	}
	
	private String getcondition(SysproQvo qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE + "1=1");
		sqlappen(sql, "proname", qvo.getProname(), qvo);
		sqlappen(sql, "id", qvo.getId());
		sqlappen(sql, "provalue", qvo.getProvalue(), qvo);
		return sql.toString();
	}
	
	public List<Syspro> query(SysproQvo qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT + "id,proname,provalue" + FROM + "sys_properties ");
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new RowMapper<Syspro>() {
			
			public Syspro mapRow(ResultSet rs, int index) throws SQLException {
				Syspro syspro = new Syspro();
				syspro.setId(rs.getInt("id"));
				syspro.setProname(rs.getString("proname"));
				syspro.setProvalue(rs.getString("provalue"));
				return syspro;
			}
		});
	}
}
