package com.ybg.mq.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.mq.dao.MQconsumerDao;
import com.ybg.mq.domian.MQconsumer;

@Repository
public class MqconsumerServiceImpl implements MQconsumerService {
	
	@Autowired
	MQconsumerDao mQconsumerDao;
	
	@Override
	public MQconsumer getIsUse() {
		return mQconsumerDao.getIsUse();
	}
}
