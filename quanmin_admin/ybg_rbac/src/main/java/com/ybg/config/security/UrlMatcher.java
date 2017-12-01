package com.ybg.config.security;

/**
 * @author https://gitee.com/YYDeament/88ybg
 *
 * @date
 * @version v1.0
 */
public interface UrlMatcher {
	
	Object compile(String paramString);
	
	boolean pathMatchesUrl(Object paramObject, String paramString);
	
	String getUniversalMatchPattern();
	
	boolean requiresLowerCaseUrl();
}
