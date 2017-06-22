/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * json工具类
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class JsonUtil {

    /**
     * 将json信息转换成jsonObject
     * 
     * @param json json数据信息
     * @return jsonObject
     */
    public static JSONObject parseJson(String json) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        if (json != null&&!(json.trim().length()==0)) {
            try {
                obj = (JSONObject) parser.parse(json);
            } catch (ParseException e) {e.printStackTrace();}
        }
        return obj;
    }

}
