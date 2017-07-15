package com.ybg.oa.department.service;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ybg.oa.department.dao.DepartmentDao;
import com.ybg.oa.department.domain.DepartmentDO;
import com.ybg.oa.department.domain.DepartmentVO;
import com.ybg.oa.department.qvo.DepartmentQuery;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;



@Repository
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	 /** 返回主键的创建
	 * 
	 * @throws Exception **/
	public DepartmentVO save(DepartmentVO bean) throws Exception{
	return departmentDao.save(bean);
	
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
		departmentDao.update(updatemap,wheremap);
	}
	
	/** 分页查询 **/
	@Override
	public	Page list(Page page,DepartmentQuery qvo)throws Exception{
		return departmentDao.list(page,qvo);
	}
	
	/** 不分页查询 **/
	@Override
	public	List<DepartmentVO> list(DepartmentQuery qvo) throws Exception{
		return departmentDao.list(qvo);
	}
	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap){
		departmentDao.remove(wheremap);
	}
	public DepartmentVO get(String id){
		return 	departmentDao.get(id);
	}
}
