package com.ybg.mq.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.mq.dao.MqConsumerDao;
import com.ybg.mq.domian.MqConsumer;

@Repository
public class MqconsumerServiceImpl implements MqConsumerService {
	
	@Autowired
	MqConsumerDao mQconsumerDao;
	
	@Override
	public MqConsumer getIsUse() {
		return mQconsumerDao.getIsUse();
	}
}
