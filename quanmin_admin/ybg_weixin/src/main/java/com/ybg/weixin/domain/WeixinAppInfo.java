package com.ybg.weixin.domain;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.base.util.SystemConstant;
import com.ybg.weixin.service.WeixinSettingService;

/** 切勿随便更改 此类 */
public class WeixinAppInfo {
	
	public static String getDomain() {
		return SystemConstant.getSystemdomain();
	}
	
	public static String getMch_id() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getMch_id();
	}
	
	public static String getNotify_url() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getNotify_url();
	}
	
	public static String getPartnerkey() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getPartnerkey();
	}
	
	public static String getSecret() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getSecret();
	}
	
	public static String getState() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getState();
	}
	
	public static String getScope() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getScope();
	}
	
	public static String getAppid() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getAppid();
	}
	
	public static String getRedirect_uri() {
		WeixinSettingService service = (WeixinSettingService) SpringContextUtils.getBean(WeixinSettingService.class);
		return service.getIsUse().getRedirect_uri();
	}
}
