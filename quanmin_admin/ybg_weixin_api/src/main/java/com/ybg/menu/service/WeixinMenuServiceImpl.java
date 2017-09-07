package com.ybg.menu.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.api.network.WeixinNW;
import com.ybg.api.service.WeixinApiService;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.menu.dao.WeixinButtonDao;
import com.ybg.menu.domain.WeixinButtonDO;

@Repository
public class WeixinMenuServiceImpl implements WeixinMenuService {
	
	@Autowired
	WeixinNW			weixinNW;
	@Autowired
	WeixinApiService	weixinApiService;
	@Autowired
	WeixinButtonDao		weixinButtonDao;
	
	private String getAccessToken() {
		return weixinApiService.getAccessToken();
	}
	
	@Override
	public void create(WeixinButtonDO bean) throws Exception {
		weixinButtonDao.create(bean);
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		weixinButtonDao.update(updatemap, wheremap);
	}
	
	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		weixinButtonDao.remove(conditionmap);
	}
	
	@Override
	public List<WeixinButtonDO> list() {
		return weixinButtonDao.list();
	}
	
	@Override
	public WeixinButtonDO get(String id) {
		return weixinButtonDao.get(id);
	}
}
