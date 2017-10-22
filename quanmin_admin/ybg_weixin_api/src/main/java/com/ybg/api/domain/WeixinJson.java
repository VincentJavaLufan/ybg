package com.ybg.api.domain;
import com.ybg.api.WeiXinErrorCodeConstant;
import net.sf.json.JSONObject;
/**
 * @author Deament
 * @date 2017年10月1日
 * **/
public class WeixinJson {
	
	String json = "";
	
	public WeixinJson(String json) {
		setJson(json);
		System.out.println(json);
	}
	
	public JSONObject getJsonObject() {
		return JSONObject.fromObject(json);
	}
	
	public boolean isSuccess() {
		JSONObject j = JSONObject.fromObject(json);
		try {
			int code = j.getInt("errcode");
			if (code == 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	
	public String getErrorMsg() {
		JSONObject j = JSONObject.fromObject(json);
		try {
			int code = j.getInt("errcode");
			return WeiXinErrorCodeConstant.errcodemap.get(code);
		} catch (Exception e) {
		}
		return WeiXinErrorCodeConstant.errcodemap.get(0);
	}
	
	public String getJson() {
		return json;
	}
	
	public void setJson(String json) {
		this.json = json;
	}
}
