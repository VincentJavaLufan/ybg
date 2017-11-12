package com.ybg.core.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.SystemConstant;
import com.ybg.rbac.support.controller.LoginProxyController;
import com.ybg.rbac.support.domain.Loginproxy;
import com.ybg.setting.domain.SocialUserVO;
import com.ybg.setting.qvo.SocialUserQuery;
import com.ybg.setting.service.SocialUserService;
import com.ybg.social.ali.AliSocialConstant;
import com.ybg.social.ali.service.AliSocialSettingService;

/**
 * @author https://gitee.com/YYDeament/88ybg
 *
 * @date
 * @version v1.0
 */
@Controller
@RequestMapping("/socialproxy/ali")
public class AliSocialController {

	@Autowired
	AliSocialSettingService aliSocialSettingService;
	@Autowired
	SocialUserService socialUserService;

	@RequestMapping("/index")
	public RedirectView index() throws Exception {
		Map<String, String> setting = aliSocialSettingService.getSetting();
		String appid = setting.get("client_ID");
		String url = "https://openauth.alipay" + AliSocialConstant.DEV + ".com/oauth2/appToAppAuth.htm";
		url += "?app_id=" + appid + "&redirect_uri=" + URLEncoder.encode(SystemConstant.getSystemdomain(), "utf-8")
				+ "%2Fsocialproxy%2Fali%2Fauth";
		System.out.println(url);
		return new RedirectView(url);
	}

	@RequestMapping("/auth")
	public String auth(@RequestParam(name = "app_auth_code") String appAuthCode, HttpServletRequest httprequest)
			throws Exception {
		Map<String, String> setting = aliSocialSettingService.getSetting();

		String appid = setting.get("client_ID");
		String clientsecret = setting.get("client_SERCRET");
		// String client_SERCRET = "n53g5dnL67vBWezFbxO1Mw==";
		// String client_SERCRET
		// ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAthpFQ0PLOIBntu/94+e5s5iOqDzPxqTCOBsjMTJqS/VZVKu20bAS56E2bNBcuHL33I+CGB1p3dhVmdam/CrmSUtmyEes/qRAe+AQE87thdWx+4+X5USPVd7CurhvQB9WmXYuIEeCDT68VSb9+aSnRhXWv0HRAcSr059Uvuw3ZQOzGmvGEEFhpFYuxV77hqil9qySjbDPBHVPiiXDh6NYVDS5jOFFRhUFraPpi3J6Bf7UN5GSWIfmJEaBPS+v2zfvYzkIMovMeCvcthBjmk1IJHpe7+2ZH3n8gfeWDA80k/PW00/Vf5N+9XxjHcP6BMmOKSOd4zSoi48ky3oYo1JL3wIDAQAB";

		String alipublickey = setting.get("alipublickey");
		// String alipublickey
		// ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwvr0XzCHq34lfQTWpoyou6b29Mtv6cY07caUKdUqm1SY5kzhhhA0bMuaFepGxSIlETPGwsp9fuqHAaSzqOiyB4YHfPUODY2xsHqxDOhZerWm0ahbK+JYP7dsh73YLEOi1bANbfm5Zc7hmqyghCzgD4e1IIJVp+9nLuyW6+jkHRbgZZpxJ/Tb5mmOJd2zoQf/2uvAMRP3Hz4cZGRST4+aVGKd99ISHw4wTzbND4M4Zw3NRK8GqOZasR0ggsLxoUvcZ/eiHQtHPLtv0nBNvni/erKcW+YTgmrtA6L8IF7vB6EmFoXH3RyGoKuTRoslCozuP4xoHx5LAAnnhfEDn/2XJwIDAQAB"
		// +
		// "";
		AlipayClient alipayClient = new DefaultAlipayClient(
				"https://openapi.alipay" + AliSocialConstant.DEV + ".com/gateway.do", appid, clientsecret, "json",
				"UTF-8", alipublickey, "RSA2");
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		request.setBizContent(
				"{" + "\"grant_type\":\"authorization_code\"," + "\"code\":\"" + appAuthCode + "\"" + "}");
		AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			SocialUserQuery qvo = new SocialUserQuery();
			qvo.setProvideruserid(response.getUserId());
			qvo.setProviderid("ali");
			// 查询是否存在该用户
			// 没有跳到绑定页面
			// 有就代理登录
			List<SocialUserVO> list = socialUserService.query(qvo);
			if (QvoConditionUtil.checkList(list)) {
				Loginproxy proxy = LoginProxyController.loginByUserId(httprequest, list.get(0).getUserid(), null);
				if (proxy.isSuccess()) {
					return "redirect:" + proxy.getRedirecturl();
				}
			} else {
				httprequest.getSession().setAttribute("ProviderSignInAttempt", "ali");
				httprequest.getSession().setAttribute("providerUserId", response.getUserId());
				return "/thirdregister";
			}

		}
		return "";
	}

}
