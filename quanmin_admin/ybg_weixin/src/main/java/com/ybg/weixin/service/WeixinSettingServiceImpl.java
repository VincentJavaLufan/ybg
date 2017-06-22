package com.ybg.weixin.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.weixin.dao.WeixinSettingDao;
import com.ybg.weixin.domain.WeiXinSetting;

@Repository
public class WeixinSettingServiceImpl implements WeixinSettingService {
	
	@Autowired
	WeixinSettingDao weixinSettingDao;
	
	@Override
	public WeiXinSetting getIsUse() {
		return weixinSettingDao.getIsUse();
	}
}
