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
import com.ybg.oa.company.domain.CompanyRegisterVO;
import com.ybg.oa.company.mapper.CompanyRegisterMapper;
import com.ybg.oa.company.qvo.CompanyRegisterQuery;

@Repository
public class CompanyRegisterDaoImpl extends BaseDao implements CompanyRegisterDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "  	  companyRegister.gmt_create, 	  companyRegister.gmt_modified, 	  companyRegister.business, 	  companyRegister.companytype, 	  companyRegister.credentialspic, 	  companyRegister.fullname, 	  companyRegister.shortname, 	  companyRegister.deal, 	  companyRegister.dealresult, id";
	private static String	QUERY_TABLE_COLUMN	= "oa_company_register  companyRegister";
	
	@Override
	public CompanyRegisterVO save(CompanyRegisterVO companyRegister) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("gmt_create", companyRegister.getGmtCreate());
		createmap.put("gmt_modified", companyRegister.getGmtModified());
		createmap.put("business", companyRegister.getBusiness());
		createmap.put("companytype", companyRegister.getCompanytype());
		createmap.put("credentialspic", companyRegister.getCredentialspic());
		createmap.put("fullname", companyRegister.getFullname());
		createmap.put("shortname", companyRegister.getShortname());
		createmap.put("deal", companyRegister.getDeal());
		createmap.put("dealresult", companyRegister.getDealresult());
		id = baseCreate(createmap, "oa_company_register", "id");
		companyRegister.setId((String) id);
		return companyRegister;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "oa_company_register");
	}
	
	@Override
	public Page list(Page page, CompanyRegisterQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new CompanyRegisterMapper()));
		}
		else {
			page.setResult(new ArrayList<CompanyRegisterVO>());
		}
		return page;
	}
	
	private String getcondition(CompanyRegisterQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("companyRegister.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("companyRegister.isdelete=0");// 默认
		// }
		sqlappen(sql, "companyRegister.id", qvo.getId());
		sqlappen(sql, "companyRegister.gmt_create", qvo.getGmtCreate());
		sqlappen(sql, "companyRegister.gmt_modified", qvo.getGmtModified());
		sqlappen(sql, "companyRegister.business", qvo.getBusiness());
		sqlappen(sql, "companyRegister.companytype", qvo.getCompanytype());
		sqlappen(sql, "companyRegister.credentialspic", qvo.getCredentialspic());
		sqlappen(sql, "companyRegister.fullname", qvo.getFullname());
		sqlappen(sql, "companyRegister.shortname", qvo.getShortname());
		sqlappen(sql, "companyRegister.deal", qvo.getDeal());
		sqlappen(sql, "companyRegister.dealresult", qvo.getDealresult());
		return sql.toString();
	}
	
	@Override
	public List<CompanyRegisterVO> list(CompanyRegisterQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new CompanyRegisterMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "oa_company_register");
	}
	
	@Override
	public CompanyRegisterVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<CompanyRegisterVO> list = getJdbcTemplate().query(sql.toString(), new CompanyRegisterMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
