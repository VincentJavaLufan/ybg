package com.baidu.oauth.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.baidu.oauth.dao.BaiduUserDao;
import com.baidu.oauth.domain.BaiduUser;
import com.baidu.oauth.qvo.BaiduUserQvo;
import com.ybg.base.jdbc.BaseMap;

@Repository
public class BaiduUserServiceImpl implements BaiduUserService {
	
	@Autowired
	BaiduUserDao baiduUserDao;
	
	public void create(BaiduUser bean) throws Exception {
		baiduUserDao.create(bean);
	}
	
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		baiduUserDao.update(updatemap, wheremap);
	}
	
	public void remove(BaseMap<String, Object> conditionmap) {
		baiduUserDao.remove(conditionmap);
	}
	
	public List<BaiduUser> query(BaiduUserQvo qvo) throws Exception {
		return baiduUserDao.query(qvo);
	}
	
	public BaiduUser getByUid(Long uid) throws Exception {
		BaiduUserQvo qvo = new BaiduUserQvo();
		qvo.setUid(uid);
		List<BaiduUser> list = baiduUserDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	@Override
	public Map<String, String> getSetting() {
		return baiduUserDao.getSetting();
	}

	@Override
	public void updateSetting(String appid, String value, String url) {
		baiduUserDao.updateSetting(appid, value, url);
		
		
	}
}
