package com.ybg.weixin.dao;

import com.ybg.weixin.domain.WXuser;

public interface WeixinUserDao {

	WXuser getByopenId(String openID);

	void create(WXuser bean) throws Exception;
}
