/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.domain;

import org.json.simple.JSONObject;

import com.baidu.api.BaiduOAuthToken;

/**
 * Session 对象，封装BaiduOAuthToken对象和User信息
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public final class Session {

    /**
     * 封装Token对象
     */
    private BaiduOAuthToken token;

    /**
     * 封装了User对象
     */
    private User user;

    public Session() {

    }

    /**
     * 通过json数据构建Session对象
     * 
     * @param json json数据
     */
    public Session(String json) {
        User user = new User(json);
        setUser(user);
        BaiduOAuthToken token = new BaiduOAuthToken(json);
        setToken(token);

    }

    public String toJSONString() {
        JSONObject obj = new JSONObject();
        getUser().putToJSONObject(obj);
        getToken().putToJSONObject(obj);
        return obj.toJSONString();
    }

    public  BaiduOAuthToken getToken() {
        return token;
    }

    public  Session setToken(BaiduOAuthToken token) {
        this.token = token;
        return this;
    }

    public  User getUser() {
        return user;
    }

    public  Session setUser(User user) {
        this.user = user;
        return this;
    }
}
