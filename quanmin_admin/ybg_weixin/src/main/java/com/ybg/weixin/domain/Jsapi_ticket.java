package com.ybg.weixin.domain;

import java.io.Serializable;

/**微信签名类**/
public class Jsapi_ticket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	url;
	private String	nonce_str;
	private String	timestamp;
	private String	signature;
	private String	jsapi_ticket;
	
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	
	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getNonce_str() {
		return nonce_str;
	}
	
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "Jsapi_ticket [url=" + url + ", nonce_str=" + nonce_str + ", timestamp=" + timestamp + ", signature=" + signature + ", jsapi_ticket=" + jsapi_ticket + "]";
	}
	
}
