package com.ybg.social;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

/** @author zhailiang */
public class ImoocConnectView extends AbstractView {
	
	private static final String CONNECTION_MODEL_KEY = "connection";
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		if (model.get(CONNECTION_MODEL_KEY) == null) {
			response.getWriter().write("<h3>解绑成功</h3>");
		}
		else {
			response.getWriter().write("<h3>绑定成功</h3>");
		}
	}
}
