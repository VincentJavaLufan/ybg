package com.ybg.weixin.login.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.ybg.api.domain.WeixinOAuthConfig;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.weixin.login.dao.WeixinUserDao;
import com.ybg.weixin.login.domain.WeixinUserVO;
import com.ybg.weixin.login.qvo.WeixinUserQuery;
import net.sf.json.JSONObject;

@Repository
public class WeixinUserServiceImpl implements WeixinUserService {
	
	@Autowired
	WeixinUserDao weixinUserDao;
	
	@Override
	public void create(WeixinUserVO bean) throws Exception {
		weixinUserDao.create(bean);
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		weixinUserDao.update(updatemap, wheremap);
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		weixinUserDao.remove(wheremap);
	}
	
	@Override
	public List<WeixinUserVO> query(WeixinUserQuery qvo) throws Exception {
		return weixinUserDao.query(qvo);
	}
	
	@Override
	public WeixinUserVO getByopenId(String openID) throws Exception {
		WeixinUserQuery qvo = new WeixinUserQuery();
		qvo.setOpenid(openID);
		List<WeixinUserVO> list = weixinUserDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	// @Cacheable 在这里在实际应用中要注释掉，因为测试的时候 会被微信拦截 访问两次会说code已经验证过了，然后得不到OPENID. 所以加上而已。
	// @Cacheable(value = "jsapiticketCache", key = "#root.method.name+#root.args[0]")
	@Override
	public String getOpen_id(String code) {
		System.out.println(code);
		StringBuffer uri = new StringBuffer();
		uri.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
		uri.append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.APPID)).append("&secret=");
		uri.append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.SECRET)).append("&code=" + code);
		uri.append("&grant_type=authorization_code");
		String result = HttpUtil.get(uri.toString());
		System.out.println(result);
		JSONObject jsonResult = JSONObject.fromObject(result);
		try {
			return jsonResult.getString("openid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		// return "oTJR-wsfb6BGhNoAtZ9LyZgiia6g";
	}
	
	@Override
	public String queryWeixinId(String userid) {
		if (!QvoConditionUtil.checkString(userid)) {
			return "";
		}
		return weixinUserDao.queryWeixinId(userid);
	}
}
