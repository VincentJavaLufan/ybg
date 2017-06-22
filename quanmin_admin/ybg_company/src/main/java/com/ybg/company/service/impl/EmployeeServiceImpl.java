package com.ybg.company.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.company.dao.EmployeeDao;
import com.ybg.company.domain.Employee;
import com.ybg.company.qvo.EmployeeQvo;
import com.ybg.company.service.EmployeeService;
import com.ybg.component.org.inter.Organization;

@Repository
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public Employee create(Employee bean) throws Exception {
		return employeeDao.create(bean);
	}
	
	@Override
	public List<Employee> query(EmployeeQvo qvo) {
		return employeeDao.query(qvo);
	}
	
	@Override
	public Page query(Page page, EmployeeQvo qvo) {
		return employeeDao.query(page, qvo);
	}
	
	@Override
	public void remove(BaseMap<String, Object> condictionmap) {
		employeeDao.remove(condictionmap);
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		employeeDao.update(updatemap, wheremap);
	}
	
	@Override
	public Organization get(String id) {
		return employeeDao.get(id);
	}
}
