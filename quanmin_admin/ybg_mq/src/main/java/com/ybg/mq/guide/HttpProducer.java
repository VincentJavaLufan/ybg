package com.ybg.mq.guide;
import java.util.Date;
import java.util.Properties;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.mq.domian.MQproducer;
import com.ybg.mq.service.MQproduceService;

public class HttpProducer {
	
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
	
	public static void sendmsg(MQproducer bean, String body) throws Exception {
		// MQproduceService mQproduceService = (MQproduceService) SpringContextUtils.getBean(MQproduceService.class);
		// MQproducer bean = mQproduceService.getIsUse();
		HttpClient httpClient = new HttpClient();
		httpClient.setMaxConnectionsPerDestination(1);
		httpClient.start();
		// Properties properties = new Properties();
		// properties.load(HttpProducer.class.getClassLoader().getResourceAsStream("user.properties"));
		String topic = bean.getTopic();
		// 请在user.properties配置您的Topic
		String url = bean.getUrl();
		// 公测集群配置为http://publictest-rest.ons.aliyun.com/
		String ak = bean.getAk();
		// 请在user.properties配置您的Ak
		String sk = bean.getSk();
		// 请在user.properties配置您的Sk
		String pid = bean.getProducerid();
		// 请在user.properties配置您的Producer ID
		String date = String.valueOf(System.currentTimeMillis());
		String sign = null;
		// String body="hello ons http";
		// String NEWLINE="\n";
		String signString = url + ak + sk + pid + date;
		for (int i = 0; i < 10; i++) {
			date = String.valueOf(System.currentTimeMillis());
			Request req = httpClient.POST(url + "?topic=" + topic + "&time=" + date + "&tag=http" + "&key=http");
			ContentProvider content = new StringContentProvider(body);
			req.content(content);
			sign = SignGuide.postSign(sk, topic, pid, body, date);
			req.header(SIGNATURE, sign);
			req.header(AK, ak);
			req.header(PRODUCERID, pid);
			ContentResponse response;
			response = req.send();
			System.out.println("send msg:" + response.getStatus() + response.getContentAsString());
		}
	}
}
