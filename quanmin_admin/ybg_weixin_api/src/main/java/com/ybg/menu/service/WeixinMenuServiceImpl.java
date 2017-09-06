package com.ybg.menu.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.api.network.WeixinNW;
import com.ybg.api.service.WeixinApiService;
import com.ybg.menu.domain.WeixintMenuDO;

@Repository
public class WeixinMenuServiceImpl implements WeixinMenuService {
	
	@Autowired
	WeixinNW			weixinNW;
	@Autowired
	WeixinApiService	weixinApiService;
	
	private String getAccessToken() {
		return weixinApiService.getAccessToken();
	}
	
	@Override
	public void create(WeixintMenuDO bean) {
		weixinNW.menu_create(bean, getAccessToken());
	}
}
