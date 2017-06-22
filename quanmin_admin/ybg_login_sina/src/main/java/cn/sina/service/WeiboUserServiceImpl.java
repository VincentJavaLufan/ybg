package cn.sina.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import cn.sina.dao.WeiboUserDao;
import cn.sina.domain.WeiboUser;
import cn.sina.qvo.WeiboUserQvo;

@Repository
public class WeiboUserServiceImpl implements WeiboUserService {
	
	@Autowired
	WeiboUserDao weiboUserDao;
	
	public void create(WeiboUser bean) throws Exception {
		weiboUserDao.create(bean);
	}
	
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		weiboUserDao.update(updatemap, wheremap);
	}
	
	public void remove(BaseMap<String, Object> wheremap) {
		weiboUserDao.remove(wheremap);
	}
	
	public List<WeiboUser> query(WeiboUserQvo qvo) {
		return weiboUserDao.query(qvo);
	}
	
	public WeiboUser get(String uid) {
		WeiboUserQvo qvo = new WeiboUserQvo();
		qvo.setUid(uid);
		List<WeiboUser> list = weiboUserDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public Map<String, String> getSetting() {
		return weiboUserDao.getSetting();
	}
}
