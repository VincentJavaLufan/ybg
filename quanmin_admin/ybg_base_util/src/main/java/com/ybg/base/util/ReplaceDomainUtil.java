package com.ybg.base.util;
import java.net.URL;

public class ReplaceDomainUtil {
	
	private ReplaceDomainUtil() {
	}
	
	/** 不管配置什么域名 都会改成和系统一致 **/
	public static String replacedomain(String oldurl) {
		try {
			java.net.URL url = new URL(oldurl);
			String host = url.getHost();// 获取主机名
			java.net.URL url2 = new URL(SystemConstant.getSystemdomain());
			String host2 = url2.getHost();// 获取主机名
			return oldurl.replace(host,host2 );
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oldurl;
	}
}
