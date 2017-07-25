package com.ybg.oa.company.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.oa.company.domain.CompanyVO;
import com.ybg.oa.company.mapper.CompanyMapper;
import com.ybg.oa.company.qvo.CompanyQuery;

@Repository
public class CompanyDaoImpl extends BaseDao implements CompanyDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "company.name,company.create_time,company.business,company.companytype,company.credentialspic,company.fullname,company.gmt_create,company.gmt_modified, id";
	private static String	QUERY_TABLE_COLUMN	= "oa_company  company";
	
	@Override
	public CompanyVO save(CompanyVO company) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("name", company.getName());
		createmap.put("create_time", company.getCreateTime());
		createmap.put("business", company.getBusiness());
		createmap.put("companytype", company.getCompanytype());
		createmap.put("credentialspic", company.getCredentialspic());
		createmap.put("fullname", company.getFullname());
		createmap.put("gmt_create", company.getGmtCreate());
		createmap.put("gmt_modified", company.getGmtModified());
		id = baseCreate(createmap, "oa_company", "id");
		company.setId((String) id);
		return company;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "oa_company");
	}
	
	@Override
	public Page list(Page page, CompanyQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new CompanyMapper()));
		}
		else {
			page.setResult(new ArrayList<CompanyVO>());
		}
		return page;
	}
	
	private String getcondition(CompanyQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("company.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("company.isdelete=0");// 默认
		// }
		sqlappen(sql, "company.id", qvo.getId());
		sqlappen(sql, "company.name", qvo.getName());
		sqlappen(sql, "company.create_time", qvo.getCreateTime());
		sqlappen(sql, "company.business", qvo.getBusiness());
		sqlappen(sql, "company.companytype", qvo.getCompanytype());
		sqlappen(sql, "company.credentialspic", qvo.getCredentialspic());
		sqlappen(sql, "company.fullname", qvo.getFullname());
		sqlappen(sql, "company.gmt_create", qvo.getGmtCreate());
		sqlappen(sql, "company.gmt_modified", qvo.getGmtModified());
		return sql.toString();
	}
	
	@Override
	public List<CompanyVO> list(CompanyQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new CompanyMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "oa_company");
	}
	
	@Override
	public CompanyVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<CompanyVO> list = getJdbcTemplate().query(sql.toString(), new CompanyMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
