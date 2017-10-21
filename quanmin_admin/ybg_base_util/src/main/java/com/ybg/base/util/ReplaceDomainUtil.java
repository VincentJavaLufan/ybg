package com.ybg.base.util;
import java.net.URL;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class ReplaceDomainUtil {
	
	private ReplaceDomainUtil() {
	}
	
	/** 不管配置什么域名 都会改成和系统一致 **/
	public static String replacedomain(String oldurl) {
		try {
			java.net.URL url = new URL(oldurl);
			// 获取主机名
			String host = url.getHost();
			java.net.URL url2 = new URL(SystemConstant.getSystemdomain());
			// 获取主机名
			String host2 = url2.getHost();
			return oldurl.replace(host, host2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oldurl;
	}
}
