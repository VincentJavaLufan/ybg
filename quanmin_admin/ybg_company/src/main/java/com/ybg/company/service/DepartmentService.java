package com.ybg.company.service;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.company.domain.Department;
import com.ybg.company.qvo.DepartmentQvo;
import com.ybg.component.org.inter.Organization;

public interface DepartmentService {
	
	Department create(Department bean) throws Exception;
	
	/** 查询所有该企业的部门 **/
	List<Department> query(DepartmentQvo qvo);
	
	void remove(BaseMap<String, Object> condictionmap);
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	Organization get(String id);
}
