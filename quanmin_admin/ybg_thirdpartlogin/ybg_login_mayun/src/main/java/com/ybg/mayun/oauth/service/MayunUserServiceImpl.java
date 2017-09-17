package com.ybg.mayun.oauth.service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.mayun.oauth.controller.MayunConfig;
import com.ybg.mayun.oauth.dao.MayunUserDao;
import com.ybg.mayun.oauth.domain.MayunUserVO;
import com.ybg.mayun.oauth.qvo.MayunUserQuery;
import net.sf.json.JSONObject;

@Repository
public class MayunUserServiceImpl implements MayunUserService {
	
	@Autowired
	MayunUserDao mayunUserDao;
	
	@Override
	public void create(MayunUserVO bean) throws Exception {
		mayunUserDao.create(bean);
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		mayunUserDao.update(updatemap, wheremap);
	}
	
	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		mayunUserDao.remove(conditionmap);
	}
	
	@Override
	public List<MayunUserVO> query(MayunUserQuery qvo) throws Exception {
		return mayunUserDao.query(qvo);
	}
	
	@Override
	public Map<String, String> getSetting() {
		return mayunUserDao.getSetting();
	}
	
	@Override
	public String getAccessToken(String code) {
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
		paramMap.put("grant_type", "authorization_code");
		paramMap.put("code", code);
		paramMap.put("client_id", MayunConfig.getValue(MayunConfig.client_ID));
		paramMap.put("redirect_uri", MayunConfig.getValue(MayunConfig.redirect_URI));
		paramMap.put("client_secret", MayunConfig.getValue(MayunConfig.client_SERCRET));
		try {
			String result = HttpUtil.post("https://git.oschina.net/oauth/token", paramMap);
			JSONObject jsonResult = JSONObject.fromObject(result);
			return jsonResult.get("access_token").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Long getMayunUserIdByToken(String access_token) {
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
		// paramMap.put("access_token", access_token);
		try {
			System.out.println(access_token);
			// String result=com.ybg.base.util.HttpRequest.sendGet("https://git.oschina.net/v5/user", "access_token="+access_token);
			String result = HttpRequest.get("http://git.oschina.net/api/v5/user?access_token=" + access_token).execute(true).body();
			JSONObject jsonResult = JSONObject.fromObject(result);
			return Long.parseLong(jsonResult.get("id").toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public MayunUserVO getUserByMayunId(String id) throws Exception {
		MayunUserQuery qvo = new MayunUserQuery();
		qvo.setMayunid(id);
		List<MayunUserVO> list = mayunUserDao.query(qvo);
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
	
	@Override
	public void updateSetting(String appid, String value, String url) {
		mayunUserDao.updateSetting(appid, value, url);
	}
	
	@Override
	public String queryMayunId(String userid) {
		if (!QvoConditionUtil.checkString(userid)) {
			return "";
		}
		return mayunUserDao.queryMayunId(userid);
	}
}
