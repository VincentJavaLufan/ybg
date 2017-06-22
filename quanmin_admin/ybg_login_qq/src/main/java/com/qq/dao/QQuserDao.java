package com.qq.dao;
import java.util.List;
import com.qq.domain.QQuser;
import com.qq.qvo.QQuserQvo;
import com.ybg.base.jdbc.BaseMap;

public interface QQuserDao {
	
	void create(QQuser bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> wheremap);
	
	List<QQuser> query(QQuserQvo qvo);
}
