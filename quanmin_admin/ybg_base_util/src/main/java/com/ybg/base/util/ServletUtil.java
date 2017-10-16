package com.ybg.base.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** Servlet的工具类。 */
public class ServletUtil {
	
	public static Log		log				= LogFactory.getLog(ServletUtil.class);
	public static String	HTML_ENCODING	= "text/html;charset=GBK";
	public static String	XML_ENCODING	= "text/xml;charset=GBK";
	
	/** 设置HTML header的文本类型及编码为"text/html;charset=gb2312"; */
	public static void setHTMLContentType(HttpServletResponse response) {
		response.setContentType(HTML_ENCODING);
	}
	
	/** 判断值是否为-1或#. 为空、-1、#返回false,否则返回true
	 * 
	 * @param requestName
	 *            .
	 * @return -1,null,# return true */
	public static boolean equalF1J(String value) {
		boolean returnTemp = false;
		if (StringUtils.isNotBlank(value)) {
			if ("-1".equals(value)) {
				returnTemp = true;
			}
			if ("#".equals(value)) {
				returnTemp = true;
			}
		}
		else {
			returnTemp = true;
		}
		return returnTemp;
	}
	
	/** 判断httpRequest传过来的值是否为-1,null,#
	 * 
	 * @param requestName
	 *            .
	 * @return -1,null,# return true */
	public static boolean equalF1J(HttpServletRequest request, String requestName) {
		return equalF1J(ServletUtil.removeSpace(request, requestName));
	}
	
	/** 设置HTML header的文本类型及编码为"text/xml;charset=gb2312"; */
	public static void setXMLContentType(HttpServletResponse response) {
		response.setContentType(XML_ENCODING);
	}
	
	/** 输出流到客户端
	 * 
	 * @param response
	 * @param ems
	 * @throws IOException */
	public static void outView(HttpServletResponse response, String ems) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(ems);
		out.flush();
		out.close();
	}
	
	/** 若request对像中不存在paramName，即返回null, 若存在，则返回已经去除两端空格的字符串
	 * 
	 * @param request
	 * @param paramName
	 * @return */
	public static String removeSpace(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (null == value) {
			return null;
		}
		else {
			return value.trim();
		}
	}
	
	/** 从request读取多个值，例如checkBox
	 * 
	 * @param request
	 * @param paramName
	 * @return */
	public static String[] removeMultiSpace(HttpServletRequest request, String paramName) {
		String[] values = request.getParameterValues(paramName);
		if (null == values) {
			return null;
		}
		for (int i = 0; i < values.length; i++) {
			values[i] = values[i].trim();
		}
		return values;
	}
	
	/** 读取参数，并把该参数转化为整型 saber修改于[2008-1-23]<br>
	*/
	public static Integer getIntParam(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (null == value) {
			// if (StringUtils.isBlank(value)) {
			return null;
		}
		else {
			return Integer.parseInt(value.trim());
		}
	}
	
	public static Integer getIntParamWithNoException(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (null == value) {
			return null;
		}
		else {
			try {
				return Integer.parseInt(value.trim());
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	/** 数字字符串的整型转换
	 * 
	 * @param str
	 *            数字字符串
	 * @param defaultVal
	 *            默认值
	 * @return 转换后的结果 */
	public final static int parseInt(String str, int defaultVal) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return defaultVal;
		}
	}
	
	/** 数字字符串的整型转换
	 * 
	 * @param str
	 *            数字字符串
	 * @param defaultVal
	 *            默认值
	 * @return 转换后的结果 */
	public final static int[] parseInt(String[] str, int defaultVal) {
		if (str == null || str.length < 1){
			return new int[0];}
		int[] result = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			result[i] = parseInt(str[i], defaultVal);
		}
		return result;
	}
	
	/** 用javascript提示相关信息，并跳转到指定页面
	 * 
	 * @param msg提示信息
	 * @param url
	 *            跳转到的路径
	 * @return
	 * @throws IOException */
	public final static void outTips(HttpServletResponse response, String msg, String url) throws IOException {
		StringBuffer tips = new StringBuffer();
		tips.append("<script language='javascript'>");
		if (msg != null) {
			tips.append("alert('" + msg.replaceAll("\\\'", "\\\\\'") + "');");
		}
		if (url != null) {
			tips.append("window.location.href='" + url + "';");
		}
		else {
			tips.append("window.history.back();");
		}
		tips.append("</script>");
		ServletUtil.outView(response, tips.toString());
	}
	
	/** 从request中取出开始页数，因为xtable中默认的参数名为page，所以这里用page 如果参数中有page，即返回page，若没有，即返回1
	 * 
	 * @param request
	 * @return */
	public static int getStartPage(HttpServletRequest request) {
		String startPageStr = ServletUtil.removeSpace(request, "page");
		int startPage = startPageStr == null ? 1 : Integer.parseInt(startPageStr);
		return startPage;
	}
	
	/** 设置页面不没缓存 */
	public static void setNoCache(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}
	
	public static Integer getIntParamDefault0(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (StringUtil.isNullAndBlank(value)) {
			// if (StringUtils.isBlank(value)) {
			return 0;
		}
		else {
			return Integer.parseInt(value.trim());
		}
	}
	
	public static Long getLongParamDefault0(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (null == value) {
			return 0L;
		}
		else {
			try {
				return Long.parseLong(value.trim());
			} catch (Exception e) {
				return 0L;
			}
		}
	}
	
	public static String getStringParamDefaultBlank(HttpServletRequest request, String paramName) {
		return getStringParamDefault(request, paramName, "");
	}
	
	public static String getStringParamDefault(HttpServletRequest request, String paramName, String deault) {
		String value = request.getParameter(paramName);
		return value == null ? deault : value;
	}
	
	/** 2006-08-07 将数组转换成字符相隔的字符串 作者ygl */
	public static String splitAry(String[] ary, String splitBy) {
		String result = "";
		if (ary != null) {
			for (int i = 0; i < ary.length; i++) {
				result += ary[i] + splitBy;
			}
			if (!result.equals("")) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}
	
	/** 将页面表单元素转化成bean:表单元素名称和bean名字一样. */
	public static <T> T getFormBean(HttpServletRequest request, Class<T> cl) {
		Map<String, String[]> map = request.getParameterMap();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String[] values = (String[]) (map.get(key));
			for (int i = 0; i < values.length; i++) {
				values[i] = trim(values[i]).replaceAll("'", "''").replaceAll("\"", "\"\"");
			}
		}
		T bean = null;
		try {
			bean = cl.newInstance();
			BeanUtils.populate(bean, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/** 返回一个对象的字符串，多数是处理字符串的 */
	public static String trim(Object obj) {
		return obj == null ? "" : String.valueOf(obj).trim();
	}
	
	/** 过滤设置到SQL语句中的字符串 */
	public final static String toDBFilter(String aStr) {
		return trim(aStr).replaceAll("\\\'", "''");
	}
	
	/** 输出参数 **/
	public static void sayParm(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		System.out.println("请求地址"+request.getRequestURI());
		for (Entry<String, String[]> me : map.entrySet()) {
			String name = me.getKey();
			String[] v = me.getValue();
			System.out.println("参数："+name + "=" + v[0]);
		}
	}
}
