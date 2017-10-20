package com.ybg.rbac.role.service;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.role.domain.RoleResDO;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.role.qvo.RoleQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public interface RoleService {
	
	/** 创建角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	SysRoleVO save(SysRoleVO role) throws Exception;
	
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
	Page list(Page page, RoleQuery qvo) throws Exception;
	
	/** 不分页查询
	 * 
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	List<SysRoleVO> list(RoleQuery qvo) throws Exception;
	
	/** 角色授权 增删改都在里面了
	 * 
	 * @param list
	 */
	void saveOrUpdateRoleRes(List<RoleResDO> list);
	
	/** 根据ID获取角色
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SysRoleVO get(String id) throws Exception;
	
	/** 根据ID删除角色
	 * 
	 * @param id
	 */
	void removebyid(String id);
}
