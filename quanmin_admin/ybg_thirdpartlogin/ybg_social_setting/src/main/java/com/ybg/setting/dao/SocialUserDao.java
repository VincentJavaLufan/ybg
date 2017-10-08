package com.ybg.setting.dao;

import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.setting.domain.SocialUserVO;
import com.ybg.setting.qvo.SocialUserQuery;

public interface SocialUserDao {

	List<SocialUserVO> query(SocialUserQuery qvo) throws Exception;
	
	void remove(BaseMap<String, Object> conditionmap);

}
