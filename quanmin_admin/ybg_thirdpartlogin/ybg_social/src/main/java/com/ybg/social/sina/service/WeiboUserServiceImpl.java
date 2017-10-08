package com.ybg.social.sina.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.social.sina.dao.WeiboUserDao;

@Repository
public class WeiboUserServiceImpl implements WeiboUserService {
	
	@Autowired
	WeiboUserDao weiboUserDao;
	
	public Map<String, String> getSetting() {
		return weiboUserDao.getSetting();
	}
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		weiboUserDao.updateSetting(appid, value, url);
	}
}
