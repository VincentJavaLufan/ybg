package com.ybg.api.service;
import java.util.List;
import java.util.Map;
import javax.print.attribute.standard.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.google.gson.JsonObject;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.ybg.api.dao.WeixinApiDao;
import com.ybg.api.domain.WeixinOAuthConfig;
import com.ybg.api.domain.menu.Menu;
import com.ybg.api.domain.menu.WeixinJson;
import net.sf.json.JSONArray;
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
	
	@Cacheable(value = "jsapiticketCache", key = "#root.method.name")
	@Override
	public String getAccessToken() {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=").append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.APPID)).append("&secret=").append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.SECRET));
		String result = HttpUtil.get(uri.toString());
		WeixinJson json = new WeixinJson(result);
		if (json.isSuccess()) {
			return json.getJsonObject().getString("access_token");
		}
		return null;
	}
	
	@Override
	public String[] getWeixinIPaddress() {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=").append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.APPID)).append("&secret=").append(WeixinOAuthConfig.getValue(WeixinOAuthConfig.SECRET));
		String result = HttpUtil.get(uri.toString());
		WeixinJson json = new WeixinJson(result);
		if (json.isSuccess()) {
			JSONArray array = json.getJsonObject().getJSONArray("ip_list");
			return (String[]) array.toArray();
		}
		return null;
	}
	
	@Override
	public WeixinJson menu_create(Menu menu, String token) {
		String jsonMenu = JSONObject.fromObject(menu).toString();
		String result = HttpUtil.post(" https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token, jsonMenu);
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson menu_get(String token) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + token);
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson menu_delete(String token) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + token);
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tags_create(String token, String name) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("name", name);
		JSONObject json2 = new JSONObject();
		json2.put("tag", json);
		String result = HttpUtil.post(uri.toString(), json2.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tags_get(String token) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + token);
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tags_update(String token, int id, String name) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/update?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		JSONObject json2 = new JSONObject();
		json2.put("tag", json);
		String result = HttpUtil.post(uri.toString(), json2.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tags_delete(String token, int id) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("id", id);
		JSONObject json2 = new JSONObject();
		json2.put("tag", json);
		String result = HttpUtil.post(uri.toString(), json2.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tag_user_get(String token, int id) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("id", id);
		JSONObject json2 = new JSONObject();
		json2.put("tag", json);
		String result = HttpUtil.get(uri.toString(), json2.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tag_members_batchtagging(String token, int tagid, List<String> openid_list) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("openid_list", openid_list);
		json.put("tagid", tagid);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tag_members_batchuntagging(String token, int tagid, List<String> openid_list) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("openid_list", openid_list);
		json.put("tagid", tagid);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tag_getidlist(String token, String openid) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("openid", openid);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson user_info_updateremark(String token, String openid, String remark) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("openid", openid);
		json.put("remark", remark);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson user_info(String token, String openid) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/user/info?access_token=");
		uri.append(token).append("&openid=").append("openid").append("&lang=zh_CN");
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson user_get(String token, String next_openid) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/user/get?access_token=");
		uri.append(token).append("&next_openid=").append(next_openid);
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tags_members_getblacklist(String token, String next_openid) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("begin_openid", next_openid);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tags_members_batchblacklist(String access_token, List<String> openid_list) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("opened_list", openid_list);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson tags_members_batchunblacklist(String access_token, List<String> openid_list) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("opened_list", openid_list);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getusersummary(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getusercumulate(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getusercumulate?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getarticlesummary(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getarticlesummary?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getarticletotal(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getarticletotal?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getuserread(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getuserread?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getuserreadhour(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getuserreadhour?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getusershare(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getusershare?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getusersharehour(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getusersharehour?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getupstreammsg(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getupstreammsg?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getupstreammsghour(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getupstreammsgweek(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getupstreammsgmonth(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getupstreammsgdist(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getupstreammsgdistweek(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getupstreammsgdistmonth(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getinterfacesummary(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson datacube_getinterfacesummaryhour(String access_token, String begin_date, String end_date) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=" + access_token);
		JSONObject json = new JSONObject();
		json.put("begin_date", begin_date);
		json.put("end_date", end_date);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson kfaccount_add(String token, String kf_account, String nickname, String password) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("kf_account", kf_account);
		json.put("nickname", nickname);
		json.put("password", password);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson kfaccount_update(String token, String kf_account, String nickname, String password) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/customservice/kfaccount/update?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("kf_account", kf_account);
		json.put("nickname", nickname);
		json.put("password", password);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson kfaccount_del(String token, String kf_account, String nickname, String password) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/customservice/kfaccount/del?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("kf_account", kf_account);
		json.put("nickname", nickname);
		json.put("password", password);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson kfaccount_uploadheadimg(String token, String kf_account, String imgpath) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WeixinJson kfaccount_getkflist(String token) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + token);
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson message_custom_send(String token, String touser, String msgtype, JSONObject msgjson) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("touser", touser);
		json.put("msgtype", msgtype);
		json.putAll(msgjson);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson message_custom_typing(String token, String touser, String command) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("touser", touser);
		json.put("command", command);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson media_uploadimg(String token, Media media) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WeixinJson media_uploadnews(String token, JsonObject articles) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("articles", articles);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson message_mass_sendall(String token, String clientmsgid) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token);
		JSONObject json = new JSONObject();
		// json.put("articles", articles);
		// XXX
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson message_mass_send(String token, List<String> touser, String msgtype, String clientmsgid) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("touser", touser);
		json.put("msgtype", msgtype);
		json.put("clientmsgid", clientmsgid);
		// XXX
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson message_mass_delete(String token, int msg_id, int article_idx) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("msg_id", msg_id);
		json.put("article_idx", article_idx);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson message_mass_preview(String token, int touser, String msgtype, JSONObject media) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("touser", touser);
		json.put("mpnews", media);
		json.put("msgtype", msgtype);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson message_mass_get(String token, int touser, String msg_id) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("msg_id", msg_id);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson template_api_set_industry(String token, String industry_id1, String industry_id2) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("industry_id1", industry_id1);
		json.put("industry_id2", industry_id2);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson template_api_get_industry(String token) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + token);
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson template_api_add_template(String token, String template_id_short) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("template_id_short", template_id_short);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson template_get_all_private_template(String token) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + token);
		String result = HttpUtil.get(uri.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson template_del_private_template(String token, String template_id) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("template_id", template_id);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson template_send(String token, String template_id, String touser, String url, String miniprogram, String appid, String pagepath, String data, String color) {
		// TODO Auto-generated method stub
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token);
		JSONObject json = new JSONObject();
		json.put("template_id", template_id);
		json.put("touser", touser);
		json.put("url", url);
		json.put("miniprogram", miniprogram);
		json.put("appid", appid);
		json.put("pagepath", pagepath);
		json.put("data", data);
		json.put("color", color);
		String result = HttpUtil.post(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
	
	@Override
	public WeixinJson get_current_autoreply_info(String token) {
		StringBuilder uri = new StringBuilder();
		uri.append("https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=" + token);
		JSONObject json = new JSONObject();
		// XXX
		String result = HttpUtil.get(uri.toString(), json.toString());
		return new WeixinJson(result);
	}
}
