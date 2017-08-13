/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.store;

import java.util.Arrays;
import java.util.List;

import com.baidu.api.domain.Session;
import com.baidu.api.utils.StringUtil;
import com.whalin.MemCached.MemCachedClient;

/**
 * 基于memcached实现token信息的存储等操作
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BaiduMemcachedStore extends BaiduStore {

    private MemCachedClient mcc;

    /**
     * 构建Memcached对象
     * 
     * @param clientId 应用的id
     * @param mcc memcached对象实例
     */
    protected BaiduMemcachedStore(String clientId, MemCachedClient mcc) {
        super(clientId);
        this.mcc = mcc;
    }

    private static List<String> supportedKeys = Arrays.asList("state", "code", "session");

    @Override
    public Session getSession() {
        String memcachedStr = getMemcachedAsString("session");
        Session session = new Session(memcachedStr);
        return session;
    }

    @Override
    public boolean setSession(Session session) {
        if (session == null) {
            return false;
        }
        String value = session.toJSONString();
        boolean flag = setMemcached("session", value);
        return flag;
    }

    @Override
    public String getState() {
        String state = getMemcachedAsString("state");
        return state;
    }

    @Override
    public boolean setState(String state) {
        if (StringUtil.isEmpty(state)) {
            return false;
        }
        boolean flage = setMemcached("state", state);
        return flage;
    }

    @Override
    public String getCode() {

        return getMemcachedAsString("code");
    }

    @Override
    public boolean setCode(String code) {
        if (StringUtil.isEmpty(code)) {
            return false;
        }
        boolean flag = setMemcached("state", code);
        return flag;
    }

    @Override
    public boolean remove(String key) {
        if (!isVariableNameValid(key)) {
            return false;
        }
        String name = sanitizeVariableName(key);
        boolean flag = mcc.delete(name);
        return flag;
    }

    @Override
    public boolean removeAll() {
        for (String key : supportedKeys) {
            remove(key);
        }
        return true;
    }

    private String getMemcachedAsString(String key) {
        if (!isVariableNameValid(key)) {
            return null;
        }
        String name = sanitizeVariableName(key);
        String value = (String) mcc.get(name);
        return value;
    }

    private boolean setMemcached(String key, String value) {
        if (!isVariableNameValid(key)) {
            return false;
        }
        String name = sanitizeVariableName(key);
        mcc.add(name, value);
        return true;
    }

    private boolean isVariableNameValid(String key) {
        if (supportedKeys.contains(key)) {
            return true;
        }
        return false;
    }

    private String sanitizeVariableName(String key) {
        StringBuilder sb = new StringBuilder(64);
        sb.append("bds_").append(clientId).append("_").append(key);
        return sb.toString();
    }
}
