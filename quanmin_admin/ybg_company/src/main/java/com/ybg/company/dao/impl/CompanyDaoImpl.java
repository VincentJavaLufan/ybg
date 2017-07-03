package com.ybg.company.dao.impl;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.company.dao.CompanyDao;
import com.ybg.company.domain.Company;
import com.ybg.company.mapper.CompanyMapper;
import com.ybg.company.qvo.CompanyQvo;
import com.ybg.component.org.inter.Organization;

@Repository
public class CompanyDaoImpl extends BaseDao implements CompanyDao {
	
	private static final String	QUERY_TABLE_COLUMN	= "oa_company.id,oa_company.name,oa_company.isauth,oa_company.create_time";
	private static String		QUERY_TABLE_NAME	= "oa_company oa_company";
	
	@Override
	public Company create(Company org) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		createmap.put("name", org.getName());
		createmap.put("createtime", org.getCreatetime());
		createmap.put("isauth", "false");
		Object id = baseCreate(createmap, "oa_company", "id");
		org.setId(id.toString());
		return org;
	}
	
	@Override
	public List<Company> query(CompanyQvo qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new CompanyMapper());
	}
	
	@Override
	public Page query(Page page, CompanyQvo qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new CompanyMapper()));
		page.setTotals(queryForInt(sql));
		return page;
	}
	
	private String getcondition(CompanyQvo qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		sqlappen(sql, "oa_company.`id`", qvo.getId());
		sqlappen(sql, "oa_company.`name`", qvo.getName());
		// sqlappen(sql, "oa_company.`isauth`", qvo.getIsauth());
		sqlappen(sql, "oa_company.`create_time`", qvo.getCreatetime());
		return sql.toString();
	}
	
	@Override
	public void remove(BaseMap<String, Object> condictionmap) {
		baseremove(condictionmap, "oa_company");
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baseupdate(updatemap, wheremap, "oa_company");
	}
	
	@Override
	public Organization get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append(" id='" + id + "'");
		List<Company> list = getJdbcTemplate().query(sql.toString(), new CompanyMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
