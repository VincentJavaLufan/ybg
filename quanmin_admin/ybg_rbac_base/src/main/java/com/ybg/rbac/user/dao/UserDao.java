package com.ybg.rbac.user.dao;
import java.util.List;
import org.springframework.stereotype.Component;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQuery;

@Component
public interface UserDao {
	
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	// sys_user
	UserVO save(UserVO user) throws Exception;
	
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
	
	/** 分页查询 **/
	// sys_user
	Page list(Page page, UserQuery qvo);
	
	/** 不分页查询 **/
	// sys_user
	List<UserVO> list(UserQuery qvo);
	
	/** 登陆 **/
	// sys_user
	UserVO login(String loginname);
	
	/** 根据条件删除 **/
	void remove(BaseMap<String, Object> wheremap);
	
	/** 清除注册不激活的用户 **/
	void removeExpired();
	
	/** 查询账户是否已存在 **/
	boolean checkisExist(UserQuery qvo);
}
