package com.ybg.weixin.dao;

import com.ybg.weixin.domain.WXuserDO;

public interface WeixinUserDao {

	WXuserDO getByopenId(String openID);

	void create(WXuserDO bean) throws Exception;
}
