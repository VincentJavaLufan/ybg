package com.ybg.setting.service;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.setting.domain.SocialUserVO;
import com.ybg.setting.qvo.SocialUserQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public interface SocialUserService {
	
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
}
