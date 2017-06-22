/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.baidu.api.BaiduApiClient;
import com.baidu.api.BaiduApiException;
import com.baidu.api.domain.User;
import com.baidu.api.service.BaseService;
import com.baidu.api.service.IUserService;
import com.baidu.api.utils.StringUtil;

/**
 * IUserService接口的实现类
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class UserServiceImpl extends BaseService implements IUserService {

    /**
     * 通过BaiduApiClient对象构建UserServiceImpl实例
     * 
     * @param invoker BaiduAPiClient对象
     */
    public UserServiceImpl(BaiduApiClient invoker) {
        super(invoker);
    }

    private final static String USER_GETLOGGEDUSER_PATH = "https://openapi.baidu.com/rest/2.0/passport/users/getLoggedInUser";

    private final static String USER_GETINFO_PATH = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";

    private final static String USER_ISAPPUSER_PATH = "https://openapi.baidu.com/rest/2.0/passport/users/isAppUser";

    private final static String USER_HASAPPPERMISSION_PATH = "https://openapi.baidu.com/rest/2.0/passport/users/hasAppPermission";

    private final static String USER_HASAPPPERMISSIONS_PATH = "https://openapi.baidu.com/rest/2.0/passport/users/hasAppPermissions";

    @Override
    public User getLoggedInUser() throws BaiduApiException {
        String apiPath = USER_GETLOGGEDUSER_PATH;
        String jsonResult = "";
        jsonResult = makeApiCall(apiPath, null, BaiduApiClient.METHOD_GET);
        return new User(jsonResult);
    }

    @Override
    public String getInfo(long uid, String[] fields) throws BaiduApiException {
        Map<String, String> params = new HashMap<String, String>();
        String apiPath = USER_GETINFO_PATH;
        if (uid < 0) {
            uid = 0;
        }
        params.put("uid", String.valueOf(uid));
        String fieldsStr = StringUtil.join(fields, ",");
        if (fieldsStr != null) {
            params.put("fields", fieldsStr);
        }
        String jsonResult = "";
        jsonResult = makeApiCall(apiPath, params, BaiduApiClient.METHOD_GET);
        return jsonResult;
    }

    @Override
    public String isAppUser(long uid, long appid) throws BaiduApiException {
        Map<String, String> params = new HashMap<String, String>();
        String apiPath = USER_ISAPPUSER_PATH;
        if (uid < 0) {
            uid = 0;
        }
        if (appid < 0) {
            appid = 0;
        }
        params.put("uid", String.valueOf(uid));
        params.put("appid", String.valueOf(appid));
        String jsonResult = "";
        jsonResult = makeApiCall(apiPath, params, BaiduApiClient.METHOD_GET);
        return jsonResult;
    }

    @Override
    public String hasAppPermission(String extPerm, long uid) throws BaiduApiException {
        Map<String, String> params = new HashMap<String, String>();
        String apiPath = USER_HASAPPPERMISSION_PATH;
        if (uid < 0) {
            uid = 0;
        }
        params.put("uid", String.valueOf(uid));
        if (!StringUtil.isEmpty(extPerm)) {
            params.put("ext_perm", extPerm);
        }
        String jsonResult = "";
        jsonResult = makeApiCall(apiPath, params, BaiduApiClient.METHOD_GET);
        return jsonResult;
    }

    @Override
    public String hasAppPermissions(String[] extPerms, long uid) throws BaiduApiException {
        Map<String, String> params = new HashMap<String, String>();
        String apiPath = USER_HASAPPPERMISSIONS_PATH;
        if (uid < 0) {
            uid = 0;
        }
        params.put("uid", String.valueOf(uid));
        String extPermsStr = StringUtil.join(extPerms, ",");
        if (!StringUtil.isEmpty(extPermsStr)) {
            params.put("ext_perms", extPermsStr);
        }
        String jsonResult = "";
        jsonResult = makeApiCall(apiPath, params, BaiduApiClient.METHOD_GET);
        return jsonResult;
    }

}
