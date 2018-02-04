package com.ybg.setting.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.setting.domain.SocialUserVO;
import com.ybg.setting.qvo.SocialUserQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public interface SocialUserDao {
	
	/** 获取社交用户
	 * 
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	List<SocialUserVO> query(SocialUserQuery qvo) throws Exception;
	
	/** 根据条件删除
	 * 
	 * @param conditionmap
	 */
	void remove(BaseMap<String, Object> conditionmap);
	
	/** 简单创建关联
	 * 
	 * @param userid
	 * @param provideruserid
	 * @param prividerid
	 */
	void create(String userid, String provideruserid, String prividerid);
	
	/** 获取单个 社交用户
	 * 
	 * @param provideruserid
	 * @param prividerid
	 * @return */
	SocialUserVO get(String provideruserid, String prividerid);
}
