package com.ybg.company.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.company.dao.CompanyDao;
import com.ybg.company.domain.Company;
import com.ybg.company.qvo.CompanyQvo;
import com.ybg.company.service.CompanyService;
import com.ybg.component.org.inter.Organization;

@Repository
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyDao company;
	
	@Override
	public Company create(Company org) throws Exception {
		return company.create(org);
	}
	
	@Override
	public List<Company> query(CompanyQvo qvo) {
		return company.query(qvo);
	}
	
	@Override
	public Page query(Page page, CompanyQvo qvo) {
		return company.query(page, qvo);
	}
	
	@Override
	public void remove(BaseMap<String, Object> condictionmap) {
		company.remove(condictionmap);
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		company.update(updatemap, wheremap);
	}
	
	@Override
	public Organization get(String id) {
		return company.get(id);
	}
}
