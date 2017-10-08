package com.ybg.social.baidu.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.social.baidu.dao.BaiduUserDao;

@Repository
public class BaiduUserServiceImpl implements BaiduUserService {
	
	@Autowired
	BaiduUserDao baiduUserDao;
	
	@Override
	public Map<String, String> getSetting() {
		return baiduUserDao.getSetting();
	}
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		baiduUserDao.updateSetting(appid, value, url);
	}
}
