package com.ybg.gen.utils.utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class HttpContextUtils {
	
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
