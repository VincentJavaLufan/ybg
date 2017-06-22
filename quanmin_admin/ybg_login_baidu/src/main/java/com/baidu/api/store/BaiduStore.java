/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.store;

import com.baidu.api.domain.Session;

/**
 * 实现存储层的抽象类，定义各种存储操作
 * 
 * @author chenhetong(chenhetong@baidu.com)
 *
 */
public abstract class BaiduStore {
    protected String clientId;
    
    protected BaiduStore(String clientId) {
	this.clientId = clientId;
    }
    /**
     * 
     * @return 获取存储在cookie中的session对象
     */
    public abstract Session getSession();
    
    /**
     * 
     * @param session  session对象封装了token和user对象
     * @return 操作状态（true/false）
     */
    public abstract boolean setSession(Session session);
    
    /**
     *  获取state状态码，保持连接和回调
     * @return state状态
     */
    public abstract String getState();
    
    /**
     * 
     * @param state state状态值
     * @return true/false
     */
    public abstract boolean setState(String state);
    
    /**
     * 获取授权
     * @return
     */
    public abstract String getCode();
    /**
     * 设置授权码，并存储在cookie中
     * @param code 
     * @return true/false
     */
    public abstract boolean setCode(String code);
    
    /**
     * 
     * @param key  cookie中对应的key值
     * @return  true/false
     */
    public abstract boolean remove(String key);
    
    /**
     * 移除所有的cookie状态
     * @return
     */
    public abstract boolean removeAll();
}
