package com.qq.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qq.dao.QQuserDao;

@Repository
public class QQuserServiceImpl implements QQuserService {
	
	@Autowired
	QQuserDao qQuserDao;
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		qQuserDao.updateSetting(appid, value, url);
	}
	
	@Override
	public Map<String, String> getSetting() {
		return qQuserDao.getSetting();
	}
}
