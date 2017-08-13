/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api;

import org.json.simple.JSONObject;

import com.baidu.api.utils.JsonUtil;

/**
 * 异常类，用于封装获取OAuth过程中的异常信息
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BaiduOAuthException extends Exception {

    private static final long serialVersionUID = -1603706048276355225L;

    private String error;

    private String errorDesp;

    public BaiduOAuthException() {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDesp() {
        return errorDesp;
    }

    public void setErrorDesp(String errorDesp) {
        this.errorDesp = errorDesp;
    }

    @Override
    public String toString() {
        return "BaiduOAuthException [error=" + error + ", errorDesp=" + errorDesp + "]";
    }

    /**
     * 通过json信息构造 异常类对象
     * 
     * @param json
     */

    public void formJson(String json) {
        JSONObject obj = JsonUtil.parseJson(json);
        if (obj != null) {
            Object objError = obj.get("error");
            Object objErrorDesp = obj.get("error_description");
            if (objError != null) {
                String error = objError.toString();
                this.setError(error);
            }
            if (objErrorDesp != null) {
                String errorDesp = objErrorDesp.toString();
                this.setErrorDesp(errorDesp);
            }
        }
    }

}
