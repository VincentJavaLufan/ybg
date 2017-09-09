package com.ybg.menu.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.api.domain.WeixinJson;
import com.ybg.api.network.WeixinNW;
import com.ybg.api.service.WeixinApiService;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.menu.WeixinButtonConstant;
import com.ybg.menu.dao.WeixinButtonDao;
import com.ybg.menu.domain.WeixinButtonVO;
import net.sf.json.JSONObject;

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
	public void create(WeixinButtonVO bean) throws Exception {
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
	public List<WeixinButtonVO> list() {
		return weixinButtonDao.list();
	}
	
	@Override
	public WeixinButtonVO get(String id) {
		return weixinButtonDao.get(id);
	}
	
	@Override
	public List<WeixinButtonVO> menulist() {
		return weixinButtonDao.menulist();
	}
	
	@Override
	public List<WeixinButtonVO> buttonlist(String parentid) throws Exception {
		return weixinButtonDao.buttonlist(parentid);
	}
	
	@Override
	public WeixinJson save(List<WeixinButtonVO> list) {
		JSONObject menu = new JSONObject();
		Collections.sort(list);// 已经排序好了
		// 一 组合成适合的树状结构。
		List<WeixinButtonVO> menuList = new ArrayList<WeixinButtonVO>();
		for (WeixinButtonVO bean : list) {
			if (bean.getParentid() == null) {
				menuList.add(bean);
			}
		}
		for (WeixinButtonVO bean : menuList) {
			for (WeixinButtonVO beanr : list) {
				if (beanr.getParentid() != null) {
					if (beanr.getIfsub() == WeixinButtonConstant.IFSUBYES && beanr.getParentid().equals(bean.getId())) {
						bean.getSub_button().add(beanr);
					}
				}
			}
		}
		menu.put("button", menuList);
		return weixinNW.menu_create(menu, getAccessToken());
	}
}
