package com.ybg.weixin.controller;
import com.ybg.weixin.domain.WeixinAppInfo;
import com.ybg.weixin.util.HttpRequestUtils;
import net.sf.json.JSONObject;

/** 微信绑定相关初始业务 **/
public class WeiXinbaseControllor {
	
	/*** 设置openid的session 用于微信菜单入口
	 * 
	 * @return session.attribute.key : weixin_user ***/
	public String getOpen_id(String code) {
		return getuserinfo(code);
	}
	
	// 封装了获得用户信息的请求方法
	private String getuserinfo(String code) {
		try {
			StringBuffer uri = new StringBuffer();
			uri.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=").append(WeixinAppInfo.getAppid()).append("&secret=").append(WeixinAppInfo.getSecret()).append("&code=" + code).append("&grant_type=authorization_code");
			String openid = "";
			JSONObject json = HttpRequestUtils.httpGet(uri.toString());
			try {
				// String access_token = "";
				// access_token = json.getString("access_token");
				openid = json.getString("openid");
				return openid;
			} catch (Exception e) {
				return null;
			}
			// StringBuffer uri2 = new StringBuffer();
			// uri2.append("https://api.weixin.qq.com/sns/userinfo?access_token=").append(access_token).append("&openid=").append(openid).append("&lang=zh_CN");
			// JSONObject userjson = HttpRequestUtils.httpGet(uri2.toString());
			// User_Info u = new User_Info();
			// try {
			// u.setCity(userjson.getString("city"));
			// u.setOpenid(userjson.getString("openid"));
			// u.setSex(userjson.getString("sex"));
			// u.setLanguage(userjson.getString("language"));
			// u.setProvince(userjson.getString("province"));
			// u.setCountry(userjson.getString("country"));
			// u.setHeadimgurl(userjson.getString("headimgurl"));
			// u.setNickname(userjson.getString("nickname"));
			// } catch (Exception e) {
			// System.out.println(userjson.toString());
			// return null;
			// }
			// // 获取unionid
			// // u.setUnionid(getuserinfo_unionid(access_token, u.getOpenid()));
			// return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 封装了获得用户信息的请求方法 开通微信开放平台之后可以使用
	// private String getuserinfo_unionid(String access_token, String open_id) {
	// try {
	// StringBuffer uri = new StringBuffer();
	// uri.append("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + open_id + "&lang=zh_CN");
	// JSONObject json = HttpRequestUtils.httpGet(uri.toString());
	// String unionid = json.getString("unionid");
	// return unionid;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
}
