/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.domain;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.baidu.api.utils.StringUtil;

/**
 * 
 * 进行batchRun调用的请求封装
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BatchRunRequest {

    //必须以https协议开头的全路径url地址
    //eg。 https://openapi.baidu.com/rest/2.0/passport/users/getLoggedInUser
    private String url;

    //请求需要的业务级参数
    private Map<String, String> params;

    //请求的方法
    private String httpMethod;

    //请求的返回值
    private String response;

    public BatchRunRequest(String url) {
        this.url = url;
    }

    public BatchRunRequest(String url, Map<String, String> params) {
        this(url);
        if (params != null && !params.isEmpty()) {
            this.params = params;
        }
    }

    public BatchRunRequest(String url,String httpMethod){
        this(url);
        this.httpMethod = httpMethod;
    }
    
    public BatchRunRequest(String url, Map<String, String> params, String httpMethod) {
        this(url, params);
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getBatchRunParam() {
        Map<String, String> ret = new HashMap<String, String>();
        //构建domain和path参数
        if (!StringUtil.isEmpty(this.url)) {
            try {
                URL urlObj = new URL(this.url);
                ret.put("domain", urlObj.getHost());
                ret.put("path", urlObj.getPath());
            } catch (MalformedURLException e) {e.printStackTrace();}
        }
        //构建params参数
        if (this.params != null && !this.params.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                try {
                    sb.append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {e.printStackTrace();}
            }
            ret.put("params", sb.toString());
        }
        //构建http_method参数
        if (!StringUtil.isEmpty(httpMethod)) {
            ret.put("http_method", this.httpMethod);
        }
        return ret;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }
}
