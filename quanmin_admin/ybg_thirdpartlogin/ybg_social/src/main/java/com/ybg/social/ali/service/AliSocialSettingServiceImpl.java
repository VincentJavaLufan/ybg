package com.ybg.social.ali.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ybg.social.ali.dao.AliSocialSettingDao;
import com.ybg.social.baidu.dao.BaiduSocialSettingDao;

/** @author Deament
 * @date 2017/10/1 **/
@Repository
public class AliSocialSettingServiceImpl implements AliSocialSettingService {
	
	@Autowired
	AliSocialSettingDao aliUserDao;
	
	@Override
	public Map<String, String> getSetting() {
		return aliUserDao.getSetting();
	}
	
	@Override
	public void updateSetting(String appid, String value, String alipublickey) {
		aliUserDao.updateSetting(appid, value, alipublickey);
	}
}
