package com.ybg.oa.company.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.oa.company.domain.CompanyVO;

public class CompanyMapper implements RowMapper<CompanyVO> {
	
	public CompanyVO mapRow(ResultSet rs, int index) throws SQLException {
		CompanyVO bean = new CompanyVO();
		bean.setId(rs.getString("Id"));
		bean.setName(rs.getString("Name"));
		bean.setCreateTime(rs.getString("CreateTime"));
		bean.setBusiness(rs.getString("Business"));
		bean.setCompanytype(rs.getInt("Companytype"));
		bean.setCredentialspic(rs.getString("Credentialspic"));
		bean.setFullname(rs.getString("Fullname"));
		bean.setGmtCreate(rs.getString("GmtCreate"));
		bean.setGmtModified(rs.getString("GmtModified"));
		return bean;
	}
}
