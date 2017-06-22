/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.store;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baidu.api.domain.Session;
import com.baidu.api.utils.StringUtil;
/**
 * 通过session来实现信息的存储
 * 
 * @author chenhetong(chenhetong@baidu.com)
 *
 */
public final class BaiduSessionStore extends BaiduStore {

    
    private HttpServletRequest request;

    private static List<String> supportedKeys = Arrays.asList("state","code","session");
    
    protected BaiduSessionStore(String clientId, HttpServletRequest request) {
        super(clientId);
        this.request = request;
    }

    @Override
    public Session getSession() {
          String sessionStr = getSessionAttribute("session");
          Session session = new Session(sessionStr);
          return session;
    }

    @Override
    public boolean setSession(Session session) {
        if (session == null) return false;
        String value = session.toJSONString();
        boolean flag= setSessionAttribute("session", value);
        return flag;
    }

    @Override
    public String getState() {
        String state = getSessionAttribute("state");
        return state;
    }

    @Override
    public boolean setState(String state) {
        if(StringUtil.isEmpty(state)){
            return false;
        }
        boolean flag = setSessionAttribute("state", state);
        return flag;
    }

    @Override
    public String getCode() {
        return  getSessionAttribute("code");
    }

    @Override
    public boolean setCode(String code) {
        if(StringUtil.isEmpty(code)){
            return false;
        }
        boolean flag = setSessionAttribute("code", code);
        return flag;
    }

    @Override
    public boolean remove(String key) {
        if(!isVariableNameValid(key)){
            return false;
        }
        String name = sanitizeVariableName(key);
        request.getSession().removeAttribute(name);
        return true;
    }

    @Override
    public boolean removeAll() {
        for (String str : supportedKeys) {
                remove(str);
        }
        return true;
    }

    private boolean setSessionAttribute(String key,String value){
         if(!isVariableNameValid(key)){
             return false;
         }
         String name = sanitizeVariableName(key);
         request.getSession().setAttribute(name, value);
         return true;
    }
    
    private String getSessionAttribute(String key){
        if(!isVariableNameValid(key)){
            return null;
        }
        String name = sanitizeVariableName(key);
        return (String)request.getSession().getAttribute(name);
    }
    
    private boolean isVariableNameValid(String key) {
        if (supportedKeys.contains(key)) {
            return true;
        }
        return false;
    }

    private String sanitizeVariableName(String key) {
        StringBuffer sb = new StringBuffer(64);
        sb.append("bds_").append(clientId).append("_").append(key);
        return sb.toString();
    }

}
