package com.ybg.oa.employee.service;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ybg.oa.employee.dao.EmployeeDao;
import com.ybg.oa.employee.domain.EmployeeDO;
import com.ybg.oa.employee.domain.EmployeeVO;
import com.ybg.oa.employee.qvo.EmployeeQuery;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;



@Repository
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	 /** 返回主键的创建
	 * 
	 * @throws Exception **/
	public EmployeeVO save(EmployeeVO bean) throws Exception{
	return employeeDao.save(bean);
	
	}
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/

	@Override
	public	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap){
		employeeDao.update(updatemap,wheremap);
	}
	
	/** 分页查询 **/
	@Override
	public	Page list(Page page,EmployeeQuery qvo)throws Exception{
		return employeeDao.list(page,qvo);
	}
	
	/** 不分页查询 **/
	@Override
	public	List<EmployeeVO> list(EmployeeQuery qvo) throws Exception{
		return employeeDao.list(qvo);
	}
	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap){
		employeeDao.remove(wheremap);
	}
	public EmployeeVO get(String id){
		return 	employeeDao.get(id);
	}
}
