package com.ybg.base.util;
/** java版的escape和unescape方法解析类.
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * 
 * @version 1.0 */
public class EncodingUtil {
	
	/** 对字符串进行编码.
	 * 
	 * @param source
	 * @return */
	public static String escape(String source) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(source.length() * 6);
		for (i = 0; i < source.length(); i++) {
			j = source.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
				tmp.append(j);
			}
			else if (j < 256) {
				tmp.append("%");
				if (j < 16) {
					tmp.append("0");
				}
				tmp.append(Integer.toString(j, 16));
			}
			else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}
	
	/** 对字符串进行解码.
	 * 
	 * @param source
	 * @return */
	public static String unescape(String source) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(source.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < source.length()) {
			pos = source.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (source.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(source.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				}
				else {
					ch = (char) Integer.parseInt(source.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			}
			else {
				if (pos == -1) {
					tmp.append(source.substring(lastPos));
					lastPos = source.length();
				}
				else {
					tmp.append(source.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	private EncodingUtil() {
	}
}
