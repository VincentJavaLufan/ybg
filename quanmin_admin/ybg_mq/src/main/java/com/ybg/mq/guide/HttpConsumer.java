package com.ybg.mq.guide;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.mq.consumer.SimpleMessage;
import com.ybg.mq.domian.MQconsumer;
import com.ybg.mq.service.MQconsumerService;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;
import com.alibaba.fastjson.JSON;

public class HttpConsumer {
	
	public static String	SIGNATURE	= "Signature";
	public static String	NUM			= "num";
	public static String	CONSUMERID	= "ConsumerID";
	public static String	PRODUCERID	= "ProducerID";
	public static String	TIMEOUT		= "timeout";
	public static String	TOPIC		= "Topic";
	public static String	AK			= "AccessKey";
	public static String	BODY		= "body";
	public static String	MSGHANDLE	= "msgHandle";
	public static String	TIME		= "time";
	
	public static void getmsg(MQconsumer bean ) throws Exception {
		// MQconsumerService mQconsumerService = (MQconsumerService) SpringContextUtils.getBean(MQconsumerService.class);
		// MQconsumer bean = mQconsumerService.getIsUse();
		HttpClient httpClient = new HttpClient();
		httpClient.setMaxConnectionsPerDestination(1);
		httpClient.start();
		// Properties properties=new Properties();
		// properties.load(HttpConsumer.class.getClassLoader().getResourceAsStream("user.properties"));
		String topic = bean.getTopic(); // 请在user.properties配置您的topic
		String url = bean.getUrl();// 公测集群配置为http://publictest-rest.ons.aliyun.com/
		String ak = bean.getAk();// 请在user.properties配置您的Ak
		String sk = bean.getSk();// 请在user.properties配置您的Sk
		String cid = bean.getConsumerid();// 请在user.properties配置您的Consumer ID
		String date = String.valueOf(new Date().getTime());
		String sign = null;
		String NEWLINE = "\n";
		System.out.println(NEWLINE + NEWLINE);
		while (true) {
			try {
				date = String.valueOf(new Date().getTime());
				Request req = httpClient.POST(url + "?topic=" + topic + "&time=" + date + "&num=" + 32);
				req.method(HttpMethod.GET);
				ContentResponse response;
				sign = SignGuide.getSign(sk, topic, cid, date);
				req.header(SIGNATURE, sign);
				req.header(AK, ak);
				req.header(CONSUMERID, cid);
				long start = System.currentTimeMillis();
				response = req.send();
				System.out.println("get cost:" + (System.currentTimeMillis() - start) / 1000 + "    " + response.getStatus() + "    " + response.getContentAsString());
				List<SimpleMessage> list = null;
				if (response.getContentAsString() != null && !response.getContentAsString().isEmpty()) {
					list = JSON.parseArray(response.getContentAsString(), SimpleMessage.class);
				}
				if (list == null || list.size() == 0) {
					Thread.sleep(100);
					continue;
				}
				System.out.println("size is :" + list.size());
				for (SimpleMessage simpleMessage : list) {
					date = String.valueOf(new Date().getTime());
					System.out.println("receive msg:" + simpleMessage.getBody() + "   born time " + simpleMessage.getBornTime());
					req = httpClient.POST(url + "?msgHandle=" + simpleMessage.getMsgHandle() + "&topic=" + topic + "&time=" + date);
					req.method(HttpMethod.DELETE);
					sign = SignGuide.deleteSign(sk, topic, cid, simpleMessage.getMsgHandle(), date);
					req.header(SIGNATURE, sign);
					req.header(AK, ak);
					req.header(CONSUMERID, cid);
					response = req.send();
					System.out.println("delete msg:" + response.toString());
				}
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}