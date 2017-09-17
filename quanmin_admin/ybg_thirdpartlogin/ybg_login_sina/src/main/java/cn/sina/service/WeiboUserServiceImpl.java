package cn.sina.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import cn.sina.dao.WeiboUserDao;
import cn.sina.domain.WeiboUserVO;
import cn.sina.qvo.WeiboUserQuery;

@Repository
public class WeiboUserServiceImpl implements WeiboUserService {
	
	@Autowired
	WeiboUserDao weiboUserDao;
	
	public void create(WeiboUserVO bean) throws Exception {
		weiboUserDao.create(bean);
	}
	
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		weiboUserDao.update(updatemap, wheremap);
	}
	
	public void remove(BaseMap<String, Object> wheremap) {
		weiboUserDao.remove(wheremap);
	}
	
	public List<WeiboUserVO> query(WeiboUserQuery qvo) throws Exception {
		return weiboUserDao.query(qvo);
	}
	
	public WeiboUserVO get(String uid) throws Exception {
		WeiboUserQuery qvo = new WeiboUserQuery();
		qvo.setUid(uid);
		List<WeiboUserVO> list = weiboUserDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public Map<String, String> getSetting() {
		return weiboUserDao.getSetting();
	}
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		weiboUserDao.updateSetting(appid, value, url);
	}
	
	@Override
	public String queryWeiboId(String userid) {
		if (!QvoConditionUtil.checkString(userid)) {
			return "";
		}
		return weiboUserDao.queryWeiboId(userid);
	}
}
