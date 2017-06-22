package com.ybg.rbac.syspro.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.rbac.syspro.domain.Syspro;
import com.ybg.rbac.syspro.qvo.SysproQvo;

public interface SysproDao {
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	// sys_user
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 不分页查询 **/
	// sys_user
	List<Syspro> query(SysproQvo qvo);
}
