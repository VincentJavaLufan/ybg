package com.ybg.config.security;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
	
	private String accessDeniedUrl;
	
	public MyAccessDeniedHandler() {
	}
	
	public MyAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendRedirect(accessDeniedUrl);
		request.getSession().setAttribute("message", "你没有权限访问这个页面!");
	}
	
	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}
	
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
}