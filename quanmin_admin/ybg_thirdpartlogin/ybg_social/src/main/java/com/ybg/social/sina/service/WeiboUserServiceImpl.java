package com.ybg.social.sina.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.social.sina.dao.WeiboSocialSettingDao;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
@Repository
public class WeiboUserServiceImpl implements WeiboUserService {
	
	@Autowired
	WeiboSocialSettingDao weiboUserDao;
	
	@Override
	public Map<String, String> getSetting() {
		return weiboUserDao.getSetting();
	}
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		weiboUserDao.updateSetting(appid, value, url);
	}
}
