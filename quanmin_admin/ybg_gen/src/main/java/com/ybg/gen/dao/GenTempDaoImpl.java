package com.ybg.gen.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.gen.domain.GenTempDO;
import com.ybg.gen.domain.GenTempVO;
import com.ybg.gen.qvo.GenTempQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ybg.base.jdbc.DataBaseConstant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class GenTempDaoImpl extends BaseDao implements GenTempDao {
	
	@Autowired
	@Qualifier(DataBaseConstant.JD_SYS)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_COLUMN	= " genTemp.description,genTemp.genfilename,genTemp.gencontext,genTemp.state, genTemp.id";
	private static String	QUERY_TABLE_NAME	= "sys_gen_temp  genTemp";
	
	@Override
	public GenTempVO save(GenTempVO genTemp) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("description", genTemp.getDescription());
		createmap.put("genfilename", genTemp.getGenfilename());
		createmap.put("gencontext", genTemp.getGencontext());
		createmap.put("state", genTemp.getState());
		id = baseCreate(createmap, "sys_gen_temp", "id");
		genTemp.setId((String) id);
		return genTemp;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "sys_gen_temp");
	}
	
	@Override
	public Page list(Page page, GenTempQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<GenTempVO>(GenTempVO.class)));
		}
		else {
			page.setResult(new ArrayList<GenTempVO>());
		}
		return page;
	}
	
	private String getcondition(GenTempQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("genTemp.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("genTemp.isdelete=0");// 默认
		// }
		sqlappen(sql, "genTemp.id", qvo.getId());
		sqlappen(sql, "genTemp.description", qvo.getDescription());
		sqlappen(sql, "genTemp.genfilename", qvo.getGenfilename());
		sqlappen(sql, "genTemp.gencontext", qvo.getGencontext());
		sqlappen(sql, "genTemp.state", qvo.getState());
		return sql.toString();
	}
	
	@Override
	public List<GenTempVO> list(GenTempQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<GenTempVO>(GenTempVO.class));
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "sys_gen_temp");
	}
	
	@Override
	public GenTempVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<GenTempVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<GenTempVO>(GenTempVO.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
