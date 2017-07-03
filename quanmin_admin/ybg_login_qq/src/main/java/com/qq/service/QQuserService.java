package com.qq.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qq.dao.QQuserDao;
import com.qq.domain.QQuserVO;
import com.qq.qvo.QQuserQuery;
import com.ybg.base.jdbc.BaseMap;

@Service
public class QQuserService {
	
	@Autowired
	QQuserDao	qQuserDao;
	
	public void create(QQuserVO bean) throws Exception {
		qQuserDao.create(bean);
	}
	
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		qQuserDao.update(updatemap, wheremap);
	}
	
	public void remove(BaseMap<String, Object> wheremap) {
		qQuserDao.remove(wheremap);
	}
	
	public List<QQuserVO> query(QQuserQuery qvo) throws Exception {
		return qQuserDao.query(qvo);
	}
	
	public QQuserVO getByopenId(String openid) throws Exception {
		QQuserQuery qvo = new QQuserQuery();
		qvo.setOpenid(openid);
		List<QQuserVO> list = qQuserDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
}
