package com.ybg.oa.department.dao;
import com.ybg.oa.department.domain.DepartmentDO;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.oa.department.domain.DepartmentDO;
import com.ybg.oa.department.domain.DepartmentVO;
import com.ybg.oa.department.qvo.DepartmentQuery;

/** @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-15 */
public interface DepartmentDao {
	
	/** 返回主键的创建
	 * 
	 * @throws Exception
	 **/
	DepartmentVO save(DepartmentVO bean) throws Exception;
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	Page list(Page page, DepartmentQuery qvo) throws Exception;
	
	/** 不分页查询
	 * 
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	List<DepartmentVO> list(DepartmentQuery qvo) throws Exception;
	
	/** 根据条件删除
	 * 
	 * @param wheremap
	 */
	void remove(BaseMap<String, Object> wheremap);
	
	/** 获取单个
	 * 
	 * @param id
	 * @return */
	DepartmentVO get(String id);
}
