package com.qq.service;
import java.util.List;
import com.qq.domain.QQuserVO;
import com.qq.qvo.QQuserQuery;
import com.ybg.base.jdbc.BaseMap;

public interface QQuserService {
	
	public void create(QQuserVO bean) throws Exception;
	
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	public void remove(BaseMap<String, Object> wheremap);
	
	public List<QQuserVO> query(QQuserQuery qvo) throws Exception;
	
	public QQuserVO getByopenId(String openid) throws Exception;
	
	String queryQQId(String userid);
}
