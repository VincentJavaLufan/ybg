package com.qq.dao;
import java.util.List;
import com.qq.domain.QQuserVO;
import com.qq.qvo.QQuserQuery;
import com.ybg.base.jdbc.BaseMap;

public interface QQuserDao {
	
	void create(QQuserVO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> wheremap);
	
	List<QQuserVO> query(QQuserQuery qvo) throws Exception;
}
