/**
 * 
 */
package com.ybg.core.properties;
/** @author zhailiang */
public class SocialProperties {
	
	private String				filterProcessesUrl	= "/auth";
	private QQProperties		qq					= new QQProperties();
	private WeixinProperties	weixin				= new WeixinProperties();
	private SinaProperties		sina				= new SinaProperties();
	private BaiduProperties		baidu				= new BaiduProperties();
	private MayunProperties		mayun				= new MayunProperties();
	
	public SinaProperties getSina() {
		return sina;
	}
	
	public void setSina(SinaProperties sina) {
		this.sina = sina;
	}
	
	public BaiduProperties getBaidu() {
		return baidu;
	}
	
	public void setBaidu(BaiduProperties baidu) {
		this.baidu = baidu;
	}
	
	public MayunProperties getMayun() {
		return mayun;
	}
	
	public void setMayun(MayunProperties mayun) {
		this.mayun = mayun;
	}
	
	public QQProperties getQq() {
		return qq;
	}
	
	public void setQq(QQProperties qq) {
		this.qq = qq;
	}
	
	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}
	
	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	public WeixinProperties getWeixin() {
		return weixin;
	}
	
	public void setWeixin(WeixinProperties weixin) {
		this.weixin = weixin;
	}
}
