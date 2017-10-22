package com.ybg.member.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.api.network.WeixinNetWork;
import com.ybg.api.service.WeixinApiService;
import com.ybg.member.domain.WeixinUserVO;

/** @author Deament
 * @date 2017/1/1 */
@Repository
public class WeixinMemberServiveImpl implements WeixinMemberService {
	
	@Autowired
	WeixinNetWork		weixinNW;
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
		// XXX 不打算做。。。。
		return null;
	}
}
