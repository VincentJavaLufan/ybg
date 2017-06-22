package com.ybg.weixinMenu;
/** 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08 */
public class MenuManager {
	
	// 第三方用户唯一凭证
	// private static final String appId_test = "wxc6df6f48e697742c";//测试公众号
	// 第三方用户唯一凭证密钥
	// private static final String appSecret_test = "c3f9c4e87a99a46d7ee71654b942a629";//测试公众号密钥
	// 第三方用户唯一凭证
	private static final String	appId		= "wxf6ae0728b84f0fe2";																		// 正式公众号
	// 第三方用户唯一凭证密钥
	private static final String	appSecret	= "";
	private static final String	preUrl		= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=";
	private static final String	lastUrl		= "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
	// private static final String domain="http://deament.ngrok.cc/tj/";
	private static final String	domain		= "http://xxpt.gdedu.gov.cn/tj/";
	
	public static void main(String[] args) {
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		String url = preUrl + domain + "weixin/weixinbound_visitor.do?acion=index" + lastUrl;
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu2(), at.getToken());
			// 判断菜单创建结果
			if (0 == result)
				System.out.println("菜单创建成功！");
			else
				System.out.println("菜单创建失败，错误码：" + result);
		}
		System.out.println(url);
	}
	
	private static Menu getMenu2() {
		String url = preUrl + domain + "weixin/weixinbound_visitor.do?acion=index" + lastUrl;
		ViewButton btn11 = new ViewButton();
		btn11.setName("绑定帐号");
		btn11.setType("view");
		btn11.setUrl(url);
		/////////////////////////////////////////////
		// String url_21=domain+"weixin/weixinbound_visitor.do?action=shuoming";
		// ViewButton btn21 = new ViewButton();
		// btn21.setName("报表状态");
		// btn21.setType("view");
		// btn21.setUrl(url_21);
		////////////
		String url_12 = domain + "weixin/weixinbound_visitor.do?action=changjianwenti";
		ViewButton btn12 = new ViewButton();
		btn12.setName("常见问题");
		btn12.setType("view");
		btn12.setUrl(url_12);
		////////////
		String url_13 = domain + "weixin/weixinbound_visitor.do?action=xxtpxtsm";
		ViewButton btn13 = new ViewButton();
		btn13.setName("填报指南");
		btn13.setType("view");
		btn13.setUrl(url_13);
		///////
		// String url_31=domain+"weixin/weixinpwd_visitor.do?acion=index";
		// ViewButton btn31 = new ViewButton();
		// btn31.setName("密码查询");
		// btn31.setType("view");
		// btn31.setUrl(url_31);
		/////
		String url_32 = preUrl + domain + "weixin/weixinqua_visitor.do?acion=index" + lastUrl;
		ViewButton btn32 = new ViewButton();
		btn32.setName("质量分析");
		btn32.setType("view");
		btn32.setUrl(url_32);
		/****************************************/
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("微信绑号");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });
		// ComplexButton mainBtn2 = new ComplexButton();
		// mainBtn2.setName("密码查询");
		// mainBtn2.setSub_button(new Button[] {btn31 });
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("质量分析");
		mainBtn3.setSub_button(new Button[] { btn32 });
		/*************************************************/
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn3 });
		return menu;
	}
	
	/** 组装菜单数据
	 * 
	 * @return */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("最新动态");
		btn11.setType("click");
		btn11.setKey("11");
		CommonButton btn21 = new CommonButton();
		btn21.setName("绑定用户");
		btn21.setType("click");
		btn21.setKey("21");
		CommonButton btn22 = new CommonButton();
		btn22.setName("解除绑定");
		btn22.setType("click");
		btn22.setKey("22");
		CommonButton btn31 = new CommonButton();
		btn31.setName("学校信息查询");
		btn31.setType("click");
		btn31.setKey("31");
		CommonButton btn32 = new CommonButton();
		btn32.setName("学生信息查询");
		btn32.setType("click");
		btn32.setKey("32");
		CommonButton btn33 = new CommonButton();
		btn33.setName("教师信息查询");
		btn33.setType("click");
		btn33.setKey("33");
		CommonButton btn34 = new CommonButton();
		btn34.setName("统计数据查询");
		btn34.setType("click");
		btn34.setKey("34");
		ViewButton btn35 = new ViewButton();
		btn35.setName("网页查询");
		btn35.setType("view");
		btn35.setUrl("http://61.142.114.229:4880/tj/cx/cxIndex.do?action=index");
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("微网导航");
		mainBtn1.setSub_button(new CommonButton[] { btn11 });
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("身份验证");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("数据查询");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34, btn35 });
		/** 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}
}