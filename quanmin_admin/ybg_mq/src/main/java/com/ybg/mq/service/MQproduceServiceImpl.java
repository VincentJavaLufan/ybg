package com.ybg.mq.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.mq.dao.MQproducerDao;
import com.ybg.mq.domian.MQproducer;

@Repository
public class MQproduceServiceImpl implements MQproduceService {
	
	@Autowired
	MQproducerDao mQproducerDao;
	
	@Override
	public MQproducer getIsUse() {
		return mQproducerDao.getIsUse();
	}
}
