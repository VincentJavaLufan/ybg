package com.ybg.member.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.api.network.WeixinNW;
import com.ybg.api.service.WeixinApiService;
import com.ybg.member.domain.WeixinUserVO;

@Repository
public class WeixinUserServiveImpl implements WeixinUserService {
	
	@Autowired
	WeixinNW			weixinNW;
	@Autowired
	WeixinApiService	weixinApiService;
	
	private String getAccessToken() {
		return weixinApiService.getAccessToken();
	}
	
	@Override
	public WeixinUserVO get(String openid) {
		weixinNW.user_info(getAccessToken(), openid);
		return null;
	}
	
	@Override
	public List<WeixinUserVO> batchget(String openid) {
		//weixinNW.uer
		return null;
	}
}
