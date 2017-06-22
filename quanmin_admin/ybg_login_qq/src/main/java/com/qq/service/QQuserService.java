package com.qq.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qq.dao.QQuserDao;
import com.qq.domain.QQuser;
import com.qq.qvo.QQuserQvo;
import com.ybg.base.jdbc.BaseMap;

@Service
public class QQuserService {
	
	@Autowired
	QQuserDao	qQuserDao;
	
	public void create(QQuser bean) throws Exception {
		qQuserDao.create(bean);
	}
	
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		qQuserDao.update(updatemap, wheremap);
	}
	
	public void remove(BaseMap<String, Object> wheremap) {
		qQuserDao.remove(wheremap);
	}
	
	public List<QQuser> query(QQuserQvo qvo) {
		return qQuserDao.query(qvo);
	}
	
	public QQuser getByopenId(String openid) {
		QQuserQvo qvo = new QQuserQvo();
		qvo.setOpenid(openid);
		List<QQuser> list = qQuserDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
}
