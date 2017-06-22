package com.ybg.base.util;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.lang.StringUtils;
/** 使用JS进行控制的翻页转换类.
 * 
 * @author 马必强 */
public class AJAXPaginate {
	
	protected int			curPage;	// 当前页
	protected int			totals;		// 总记录数
	protected int			perPage;	// 每页大小
	protected int			totalPages;	// 总页数
	protected StringBuffer	roll;		// 解析的结果
	
	public AJAXPaginate(int totals, int curPage, int perPage) {
		this.totals = totals;
		this.curPage = curPage;
		this.perPage = perPage;
		if (totals / this.perPage < 1) {
			this.totalPages = 1;
		}
		else {
			this.totalPages = this.totals / perPage;
			if (this.totals % perPage != 0) {
				this.totalPages += 1;
			}
		}
	}
	//
	// /** 设置URL地址.参数请自行加在地址后面.
	// *
	// * @param url
	// * 请求地址
	// * @param funcName
	// * 跳转的JS函数的名称 */
	// public void setUrl(String url, String funcName) {
	// parse(parseURL(url), funcName);
	// }
	// public void setUrl(HttpServletRequest request, String funcName) {
	// setUrl(request.getRequestURL().toString() + "?" + getQueryString(request), funcName);
	// }
	//
	// /** 通过request获取请求地址
	// *
	// * @param request
	// * @param paras
	// * @param funcName */
	// public void setUrl(HttpServletRequest request, String paras, String funcName) {
	// String url = request.getRequestURI() + "?" + paras;
	// setUrl(url, funcName);
	// }
	//
	// protected String getQueryString(HttpServletRequest request) {
	// Map<String, String[]> params = request.getParameterMap();
	// String queryString = "";
	// for (String key : params.keySet()) {
	// String[] values = params.get(key);
	// for (int i = 0; i < values.length; i++) {
	// String value = values[i];
	// queryString += key + "=" + value + "&";
	// }
	// }
	// // 去掉最后一个空格
	// queryString = queryString.substring(0, queryString.length() - 1);
	// return queryString;
	// }
	//
	// public void setUrl(HttpServletRequest request) {
	// String uri = request.getRequestURI();
	// String param = StringUtils.trimToEmpty(getQueryString(request)).intern();
	// String url = uri + param == "" ? "" : "?" + param;
	// setUrl(url, null);
	// }
	//
	// /** 解析并返回最终的URL地址,避免了page重复的问题.
	// *
	// * @param url
	// * @return */
	// private String parseURL(String url) {
	// url = StringUtil.trim(url);
	// int index = url.indexOf("?");
	// if (index == -1 || index == url.length() - 1)
	// return url;
	// String query = StringUtils.trimToEmpty(url.substring(index + 1));
	// String[] parts = StringUtil.trim(query.split("&"));
	// StringBuilder buf = new StringBuilder();
	// for (int i = 0; i < parts.length; i++) {
	// String[] tmp = StringUtil.trim(parts[i].split("="));
	// if (tmp.length != 2) {
	// buf.append(parts[i] + "&");
	// continue;
	// }
	// if (tmp[0].equals("page"))
	// continue;
	// buf.append(tmp[0] + "=" + Encoding.escape(tmp[1]) + "&");
	// }
	// if (buf.length() > 0 && buf.charAt(buf.length() - 1) == '&') {
	// buf.deleteCharAt(buf.length() - 1);
	// }
	// return url.substring(0, index + 1) + buf.toString();
	// }
	//
	// /** 获取翻页控制代码.
	// *
	// * @return */
	// public String getRoll() {
	// return this.roll.toString();
	// }
	//
	// /** 覆盖toString方法. */
	// @Override
	// public String toString() {
	// return getRoll();
	// }
	//
	// /** 解析翻页控制 */
	// protected void parse(String url, String funcName) {
	// funcName = funcName == null ? "jump" : funcName;
	// String paras = url + (url.indexOf('?') == -1 ? "?" : "&") + "page=";
	// boolean pre = curPage > 1; // 是否可以向前翻页
	// boolean end = curPage < totalPages; // 是否可以向后翻页
	// roll = new StringBuffer("[");
	// if (pre) {
	// roll.append("<a href=\"javascript:" + funcName);
	// roll.append("('" + paras + "',1);\">");
	// }
	// roll.append("首页");
	// if (pre)
	// roll.append("</a>");
	// roll.append(" | ");
	// if (pre) {
	// roll.append("<a href=\"javascript:" + funcName);
	// roll.append("('" + paras + "'," + (curPage - 1) + ");\">");
	// }
	// roll.append("上一页");
	// if (pre)
	// roll.append("</a>");
	// roll.append(" | ");
	// if (end) {
	// roll.append("<a href=\"javascript:" + funcName);
	// roll.append("('" + paras + "'," + (curPage + 1) + ");\">");
	// }
	// roll.append("下一页");
	// if (end)
	// roll.append("</a>");
	// roll.append(" | ");
	// if (end) {
	// roll.append("<a href=\"javascript:" + funcName);
	// roll.append("('" + paras + "'," + totalPages + ");\">");
	// }
	// roll.append("末页");
	// if (end)
	// roll.append("</a>");
	// roll.append("]");
	// roll.append("总数:" + this.totals + "&nbsp;");
	// roll.append("页数:");
	// roll.append("<select onchange=\"" + funcName);
	// roll.append("('" + paras + "',this.value)\">");
	// for (int i = 0; i < totalPages; i++) {
	// roll.append("<option value='" + (i + 1) + "'");
	// if (curPage == i + 1) {
	// roll.append(" selected");
	// }
	// roll.append(">" + (i + 1) + "/" + totalPages);
	// roll.append("</option>");
	// }
	// roll.append("</select>");
	// }
}
