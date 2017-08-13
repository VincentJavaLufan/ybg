/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api;

import org.json.simple.JSONObject;

import com.baidu.api.utils.JsonUtil;

/**
 * AccessToken 封装类
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BaiduOAuthToken {

    /**
     * 过期时间
     */
    private int expiresIn;

    /**
     * 刷新token的令牌
     */
    private String refreshToken;

    /**
     * accessToken信息
     */
    private String accessToken;

    /**
     * session密钥，用于进行http连接进行签名
     */
    private String sessionSecret;

    /**
     * sessionKey 用户http连接请求
     */
    private String sessionKey;

    /**
     * scope 请求权限
     */
    private String scope;

    public BaiduOAuthToken() {
    }

    /**
     * 通过json数据创建AccessToken对象
     * 
     * @param json json数据
     */
    public BaiduOAuthToken(String json) {
        JSONObject obj = JsonUtil.parseJson(json);
        if (obj != null) {
            Object objExpire = obj.get("expires_in");
            Object objRefresh = obj.get("refresh_token");
            Object objAccess = obj.get("access_token");
            Object objSessionSecret = obj.get("session_secret");
            Object objSessionKey = obj.get("session_key");
            Object objScope = obj.get("scope");
            if (objAccess != null) {
                this.setAccessToken(objAccess.toString());
            }
            if (objExpire != null) {
                this.setExpiresIn(Integer.valueOf(objExpire.toString()));
            }
            if (objRefresh != null) {
                this.setRefreshToken(objRefresh.toString());
            }
            if (objScope != null) {
                this.setScope(objScope.toString());
            }
            if (objSessionKey != null) {
                this.setSessionKey(objSessionKey.toString());
            }
            if (objSessionSecret != null) {
                this.setSessionSecret(objSessionSecret.toString());
            }
        }
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSessionSecret() {
        return sessionSecret;
    }

    public void setSessionSecret(String sessionSecret) {
        this.sessionSecret = sessionSecret;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @SuppressWarnings("unchecked")
    public void putToJSONObject(JSONObject obj) {
        if (obj != null) {
            obj.put("expires_in", getExpiresIn());
            obj.put("refresh_token", getRefreshToken());
            obj.put("access_token", getAccessToken());
            obj.put("session_secret", getSessionSecret());
            obj.put("session_key", getSessionKey());
            obj.put("scope", getScope());
        }
    }

    @Override
    public String toString() {
        return "OAuthAccessToken [expiresIn=" + expiresIn + ", refreshToken=" + refreshToken
                + ", accessToken=" + accessToken + ", sessionSecret=" + sessionSecret
                + ", sessionKey=" + sessionKey + ", scope=" + scope + "]";
    }

}
