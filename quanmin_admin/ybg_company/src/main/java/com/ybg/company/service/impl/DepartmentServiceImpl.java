package com.ybg.company.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.company.dao.DepartmentDao;
import com.ybg.company.domain.Department;
import com.ybg.company.qvo.DepartmentQvo;
import com.ybg.company.service.DepartmentService;
import com.ybg.component.org.inter.Organization;

@Repository
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentDao departmentdao;
	
	@Override
	public Department create(Department bean) throws Exception {
		return departmentdao.create(bean);
	}
	
	@Override
	public List<Department> query(DepartmentQvo qvo) {
		return departmentdao.query(qvo);
	}
	
	@Override
	public void remove(BaseMap<String, Object> condictionmap) {
		departmentdao.remove(condictionmap);
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		departmentdao.update(updatemap, wheremap);
	}
	
	@Override
	public Organization get(String id) {
		return departmentdao.get(id);
	}
}
