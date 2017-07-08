package com.ybg.oa.company.service;
import com.ybg.oa.company.domain.CompanyRegisterDO;
import com.ybg.oa.company.domain.CompanyRegisterVO;
import com.ybg.oa.company.qvo.CompanyRegisterQuery;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/** @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-08 */
public interface CompanyRegisterService {
	
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	CompanyRegisterVO save(CompanyRegisterVO bean) throws Exception;
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	// sys_role
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 分页查询 **/
	// sys_role
	Page list(Page page, CompanyRegisterQuery qvo) throws Exception;
	
	/** 不分页查询 **/
	// sys_role
	List<CompanyRegisterVO> list(CompanyRegisterQuery qvo) throws Exception;
	
	/** 根据条件删除 **/
	void remove(BaseMap<String, Object> wheremap);
	
	CompanyRegisterVO get(String id);
}
