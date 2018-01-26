package com.ybg.api.service;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.ybg.api.dao.WeixinApiDao;
import com.ybg.api.domain.WeixinJson;
import com.ybg.api.domain.WeixinOpenAuthorizationConfig;
import com.ybg.api.network.WeixinNetWork;
import cn.hutool.http.HttpUtil;
import net.sf.json.JSONObject;

/** @author Deament
 * @date 2017年10月22日 **/
@Repository
public class WeixinApiServiceImpl implements WeixinApiService {
	
	@Autowired
	WeixinApiDao	weixinApiDao;
	@Autowired
	WeixinNetWork	weixinNW;
	
	@Override
	public Map<String, String> getSetting() {
		return weixinApiDao.getSetting();
	}
	
	@Override
	public String getOpenidByCode(String code) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
		uri.append(WeixinOpenAuthorizationConfig.getValue(WeixinOpenAuthorizationConfig.APPID));
		uri.append("&secret=").append(WeixinOpenAuthorizationConfig.getValue(WeixinOpenAuthorizationConfig.SECRET));
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
	
	@Cacheable(value = "jsapiticketCache", key = "#root.method.name")
	@Override
	public String getAccessToken() {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
		uri.append(WeixinOpenAuthorizationConfig.getValue(WeixinOpenAuthorizationConfig.APPID));
		uri.append("&secret=").append(WeixinOpenAuthorizationConfig.getValue(WeixinOpenAuthorizationConfig.SECRET));
		String result = HttpUtil.get(uri.toString());
		WeixinJson json = new WeixinJson(result);
		if (json.isSuccess()) {
			return json.getJsonObject().getString("access_token");
		}
		return null;
	}
	// 微信访问的接口解析成VO类 还没写
	
	@Override
	public void updateSetting(String appid, String value) {
		weixinApiDao.updateSetting(appid, value);
	}
}
