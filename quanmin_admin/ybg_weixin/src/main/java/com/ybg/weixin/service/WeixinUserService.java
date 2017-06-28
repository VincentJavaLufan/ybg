package com.ybg.weixin.service;

import com.ybg.weixin.domain.WXuserDO;

public interface WeixinUserService {

	WXuserDO getByopenId(String openID);

	void create(WXuserDO bean) throws Exception;
}
