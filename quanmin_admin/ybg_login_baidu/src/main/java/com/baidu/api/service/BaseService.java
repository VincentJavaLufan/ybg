/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.service;

import java.util.Map;

import com.baidu.api.BaiduApiClient;
import com.baidu.api.BaiduApiException;

/**
 * 接口服务的基类信息
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BaseService {

    private BaiduApiClient invoker = null;

    public BaseService(BaiduApiClient invoker) {
        this.invoker = invoker;
    }

    /**
     * 调用api请求
     * 
     * @param apiPath api请求的url
     * @param apiType 调用的api类型 rest、file、public
     * @param params 调用api时传递的参数
     * @param method 调用api的方法GET/POST
     * @return 响应信息，以json格式返回
     * @throws BaiduApiException
     */
    public String makeApiCall(String apiPath, Map<String, String> params,
            String method) throws BaiduApiException {
        String retStr = this.invoker.request(apiPath, params, method);
        return retStr;
    }

}
