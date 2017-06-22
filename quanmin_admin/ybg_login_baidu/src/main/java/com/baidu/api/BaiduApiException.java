/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api;

import org.json.simple.JSONObject;

import com.baidu.api.utils.JsonUtil;

/**
 * api调用的异常类
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BaiduApiException extends Exception {

    private static final long serialVersionUID = -71058500666371407L;

    private String errorCode;

    private String errorMsg;

    public BaiduApiException() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BaiduApiException [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
    }

    /**
     * 通过json数据构建BaiduApiException类
     * 
     * @param json
     */
    public void formJson(String json) {
        JSONObject obj = JsonUtil.parseJson(json);
        if (obj != null) {
            Object objErrorCode = obj.get("error_code");
            Object objErrorMsg = obj.get("error_msg");
            if (objErrorCode != null) {
                this.setErrorCode(objErrorCode.toString());
            }
            if (objErrorMsg != null) {
                this.setErrorMsg(objErrorMsg.toString());
            }
        }

    }

}
