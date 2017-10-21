package com.ybg.social.baidu.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.social.baidu.dao.BaiduSocialSettingDao;

/** @author Deament
 * @date 2017/10/1 **/
@Repository
public class BaiduSocialSettingServiceImpl implements BaiduSocialSettingService {
	
	@Autowired
	BaiduSocialSettingDao baiduUserDao;
	
	@Override
	public Map<String, String> getSetting() {
		return baiduUserDao.getSetting();
	}
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		baiduUserDao.updateSetting(appid, value, url);
	}
}
