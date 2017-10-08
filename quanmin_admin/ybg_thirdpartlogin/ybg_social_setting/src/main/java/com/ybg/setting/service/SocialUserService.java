package com.ybg.setting.service;

import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.setting.domain.SocialUserVO;
import com.ybg.setting.qvo.SocialUserQuery;

public interface SocialUserService {
	
List<SocialUserVO> query(SocialUserQuery qvo) throws Exception;
	
	void remove(BaseMap<String, Object> conditionmap);
}
