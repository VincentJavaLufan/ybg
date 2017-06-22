package com.ybg.base.jdbc.util;
import java.security.MessageDigest;

/** 加密工具
 * 
 * @author 张但 */
public class EncryptUtil {
	
	/** MD5加密
	 * 
	 * @param s
	 *            源字符串
	 * @return 加密后的字符串 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private EncryptUtil() {
		// 禁止实例化
	}
}
