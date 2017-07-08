package com.ybg.oa.company.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.oa.company.domain.CompanyRegisterVO;

public class CompanyRegisterMapper implements RowMapper<CompanyRegisterVO> {
	

	public CompanyRegisterVO mapRow(ResultSet rs, int index) throws SQLException {
		CompanyRegisterVO bean = new CompanyRegisterVO();
						bean.setId(rs.getString("Id"));
								bean.setGmtCreate(rs.getString("GmtCreate"));
								bean.setGmtModified(rs.getString("GmtModified"));
								bean.setBusiness(rs.getString("Business"));
								bean.setCompanytype(rs.getInt("Companytype"));
								bean.setCredentialspic(rs.getString("Credentialspic"));
								bean.setFullname(rs.getString("Fullname"));
								bean.setShortname(rs.getString("Shortname"));
								bean.setDeal(rs.getInt("Deal"));
								bean.setDealresult(rs.getString("Dealresult"));
						return bean;
	}
}
