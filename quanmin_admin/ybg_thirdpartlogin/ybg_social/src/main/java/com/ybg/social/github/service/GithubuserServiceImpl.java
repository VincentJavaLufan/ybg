package com.ybg.social.github.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.social.github.dao.GithubuserDao;
import com.ybg.social.github.dao.GithubuserDaoImpl;

@Repository
public class GithubuserServiceImpl implements GithubuserService {
	
	@Autowired
	GithubuserDao githubuserDao;
	
	@Override
	public Map<String, String> getSetting() {
		return githubuserDao.getSetting();
	}
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		githubuserDao.updateSetting(appid, value, url);
	}
}
