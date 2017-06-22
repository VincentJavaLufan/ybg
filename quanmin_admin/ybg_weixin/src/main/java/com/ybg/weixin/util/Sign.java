//package cn.qtone.weixin.util;
//import java.util.UUID;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Formatter;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.io.UnsupportedEncodingException;
//import net.sf.ehcache.CacheManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//import com.ybg.weixin.app.service.User_InfoService;
//import cn.qtone.weixin.domain.Jsapi_ticket;
//
//@Component
//public class Sign {
//	
//	@Autowired
//	User_InfoService	user_InfoService;
//	@Autowired
//	CacheManager		cacheManager;
//	String				url;
//	
//	public void setUrl(String url) {
//		this.url = url;
//	}
//	
//	public void setUser_InfoService(User_InfoService user_InfoService) {
//		this.user_InfoService = user_InfoService;
//	}
//	
//	
//	
//	public static Jsapi_ticket sign(String jsapi_ticket, String url) {
//		// Map<String, String> ret = new HashMap<String, String>();
//		String nonce_str = create_nonce_str();
//		String timestamp = create_timestamp();
//		String string1;
//		String signature = "";
//		// 注意这里参数名必须全部小写，且必须有序
//		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
//		System.out.println(string1);
//		try {
//			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//			crypt.reset();
//			crypt.update(string1.getBytes("UTF-8"));
//			signature = byteToHex(crypt.digest());
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		Jsapi_ticket bean = new Jsapi_ticket();
//		bean.setUrl(url);
//		bean.setNonce_str(nonce_str);
//		bean.setTimestamp(timestamp);
//		bean.setSignature(signature);
//		bean.setJsapi_ticket(jsapi_ticket);
//		return bean;
//	}
//	
//	private static String byteToHex(final byte[] hash) {
//		Formatter formatter = new Formatter();
//		for (byte b : hash) {
//			formatter.format("%02x", b);
//		}
//		String result = formatter.toString();
//		formatter.close();
//		return result;
//	}
//	
//	private static String create_nonce_str() {
//		return UUID.randomUUID().toString();
//	}
//	
//	public User_InfoService getUser_InfoService() {
//		return user_InfoService;
//	}
//	
//	public String getUrl() {
//		return url;
//	}
//	
//	private static String create_timestamp() {
//		return Long.toString(System.currentTimeMillis() / 1000);
//	}
//	
//	public Sign() {
//	}
//}
