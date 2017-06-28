package com.ybg.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.weixin.dao.WeixinUserDao;
import com.ybg.weixin.domain.WXuserDO;

@Repository
public class WeixinUserServiceImpl implements WeixinUserService{
	@Autowired
	 WeixinUserDao weixinUserDao ;
	
	@Override
	public WXuserDO getByopenId(String openID) {
		
		return weixinUserDao.getByopenId(openID);
	}

	@Override
	public void create(WXuserDO bean) throws Exception {
		weixinUserDao.create(bean);
		
	}
}
