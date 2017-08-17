package com.ybg.api.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.ybg.api.dao.WeixinApiDao;
import com.ybg.api.domain.WeixinOAuthConfig;
import net.sf.json.JSONObject;

@Repository
public class WeixinApiServiceImpl implements WeixinApiService {
	
	@Autowired
	WeixinApiDao weixinApiDao;
	
	@Override
	public Map<String, String> getSetting() {
		return weixinApiDao.getSetting();
	}
	
	@Override
	public String getOpenidByCode(String code) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
		uri.append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.APPID));
		uri.append("&secret=").append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.SECRET));
		uri.append("&code=" + code).append("&grant_type=authorization_code");
		try {
			String result = HttpUtil.get(uri.toString());
			JSONObject json = JSONObject.fromObject(result);
			return json.getString("open_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
