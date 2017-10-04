package com.ybg.social.baidu.api;
import org.json.simple.JSONObject;
import com.baidu.api.utils.JsonUtil;

public class BaiduUserInfo {
	
	private String	id;
	/** 用户uid */
	private long	uid	= 0;
	/** 用户名 */
	private String	uname;
	/** 用户头像加密串 */
	private String	portrait;
	
	public BaiduUserInfo() {
	}
	
	/** 通过json数据，构建User类
	 * 
	 * @param json */
	public BaiduUserInfo(String json) {
		JSONObject obj = JsonUtil.parseJson(json);
		if (obj != null) {
			Object objUid = obj.get("uid");
			Object objUname = obj.get("uname");
			Object objPortrait = obj.get("portrait");
			if (objPortrait != null) {
				this.setPortrait(objPortrait.toString());
			}
			if (objUid != null) {
				this.setUid(Long.valueOf(objUid.toString()));
			}
			if (objUname != null) {
				this.setUname(objUname.toString());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void putToJSONObject(JSONObject obj) {
		if (obj != null) {
			obj.put("uid", getUid());
			obj.put("uname", getUname());
			obj.put("portrait", getPortrait());
		}
	}
	
	public long getUid() {
		return uid;
	}
	
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getPortrait() {
		return portrait;
	}
	
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", portrait=" + portrait + "]";
	}
}
