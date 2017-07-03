package com.ybg.company.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.company.domain.Employee;
import com.ybg.company.qvo.EmployeeQvo;
import com.ybg.component.org.inter.Organization;

public interface EmployeeDao {
	
	Employee create(Employee bean) throws Exception;
	
	List<Employee> query(EmployeeQvo qvo) throws Exception;
	
	Page query(Page page, EmployeeQvo qvo) throws Exception;
	
	void remove(BaseMap<String, Object> condictionmap);
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	Organization get(String id);
}
