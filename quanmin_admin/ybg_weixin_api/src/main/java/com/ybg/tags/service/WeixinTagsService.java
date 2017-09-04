package com.ybg.tags.service;
import java.util.List;
import com.ybg.tags.domain.WeixinTagsVO;

public interface WeixinTagsService {
	
	public void create(WeixinTagsVO bean);
	
	public List<WeixinTagsVO> list();
	
	public void update(Integer id, String name);
	
	public void remove(Integer id);
	
	public void batchuntagging(List<String> openids, Integer tagid);
	
	public void batchtagging(List<String> openids, Integer tagid);
}
