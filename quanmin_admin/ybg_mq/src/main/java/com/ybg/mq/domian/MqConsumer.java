package com.ybg.mq.domian;
import java.io.Serializable;

public class MqConsumer implements Serializable {
	
	String	id;
	String	topic;
	String	url;
	String	ak;
	String	sk;
	String	consumerid;
	
	
	public String getConsumerid() {
		return consumerid;
	}

	
	public void setConsumerid(String consumerid) {
		this.consumerid = consumerid;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getAk() {
		return ak;
	}
	
	public void setAk(String ak) {
		this.ak = ak;
	}
	
	public String getSk() {
		return sk;
	}
	
	public void setSk(String sk) {
		this.sk = sk;
	}
}
