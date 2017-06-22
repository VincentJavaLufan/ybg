/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.store;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.baidu.api.BaiduOAuthToken;
import com.baidu.api.domain.Session;

/** 通过Cookie实现对token信息和User信息的存储
 * 
 * @author chenhetong(chenhetong@baidu.com) */
public final class BaiduCookieStore extends BaiduStore {
	
	private HttpServletRequest	request;
	private HttpServletResponse	response;
	private static List<String>	supportedKeys	= Arrays.asList("state", "code", "session");
	
	/** 创建Cookie Store的存储类
	 * 
	 * @param clientId
	 *            应用id
	 * @param request
	 *            HttpServletRequest对象，用于获取cookie对象
	 * @param response
	 *            HttpServletResponse对象，用于添加cookie对象 */
	public BaiduCookieStore(String clientId, HttpServletRequest request, HttpServletResponse response) {
		super(clientId);
		this.request = request;
		this.response = response;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	/** 如果cookie对象为空，或者cookie中不含有该属性返回空 */
	@Override
	public Session getSession() {
		String key = sanitizeVariableName("session");
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie ck : cookies) {
			if (key.equals(ck.getName())) {
				String val = "";
				try {
					val = URLDecoder.decode(ck.getValue(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return new Session(val);
			}
		}
		return null;
	}
	
	@Override
	public boolean setSession(Session session) {
		String key = sanitizeVariableName("session");
		String value = session.toJSONString();
		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie ck = new Cookie(key, value);
		ck.setPath("/");
		BaiduOAuthToken token = session.getToken();
		if (null != token && token.getExpiresIn() != 0) {
			ck.setMaxAge(token.getExpiresIn());
		}
		else {
			ck.setMaxAge(3600 * 24);
		}
		response.addCookie(ck);
		return true;
	}
	
	@Override
	/**
	 *  如果cookie对象为空，或cookie中不含有该属性返回空
	 */
	public String getState() {
		return getCookieAsString("state");
	}
	
	@Override
	public boolean setState(String state) {
		return setCookie("state", state);
	}
	
	/** 如果cookie对象为空，或cookie中不含有该属性返回空 */
	@Override
	public String getCode() {
		return getCookieAsString("code");
	}
	
	@Override
	public boolean setCode(String code) {
		return setCookie("code", code);
	}
	
	@Override
	public boolean remove(String key) {
		if (!isVariableNameValid(key)) {
			return false;
		}
		String name = sanitizeVariableName(key);
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				cookie.setValue(null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return true;
	}
	
	@Override
	public boolean removeAll() {
		remove("code");
		remove("state");
		remove("session");
		return true;
	}
	
	private String getCookieAsString(String key) {
		String name = sanitizeVariableName(key);
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie ck : cookies) {
			if (name.equals(ck.getName())) {
				String value = ck.getValue();
				if (value == null) {
					return null;
				}
				try {
					value = URLDecoder.decode(value, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return value;
			}
		}
		return null;
	}
	
	private boolean setCookie(String key, String value) {
		if (!isVariableNameValid(key)) {
			return false;
		}
		String name = sanitizeVariableName(key);
		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie ck = new Cookie(name, value);
		ck.setPath("/");
		ck.setMaxAge(3600 * 24);
		response.addCookie(ck);
		return true;
	}
	
	private boolean isVariableNameValid(String key) {
		if (supportedKeys.contains(key)) {
			return true;
		}
		return false;
	}
	
	private String sanitizeVariableName(String key) {
		StringBuffer sb = new StringBuffer(64);
		sb.append("bds_").append(clientId).append("_").append(key);
		return sb.toString();
	}
}
