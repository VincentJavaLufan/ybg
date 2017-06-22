package com.ybg.weixin.service;

import com.ybg.weixin.domain.WXuser;

public interface WeixinUserService {

	WXuser getByopenId(String openID);

	void create(WXuser bean) throws Exception;
}
