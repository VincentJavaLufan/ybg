/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.utils;

/**
 * 字符串工具类
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class StringUtil {

    /**
     * 判断字符串为空
     * 
     * @param str 字符串信息
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符数组，不为空
     * 
     * @param values 字符数组
     * @return true or false
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
                if (result == false) {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * join方法将 Stirng数组，通过separater分隔符进行分割
     * 
     * @param resource  源数组
     * @param separater 分隔符
     * @return
     */
    public static String join(String[] resource, String separater) {
        if(resource==null||resource.length==0){
            return null;
        }
        int len = resource.length;
        StringBuilder sb = new StringBuilder();
        if (len > 0) {
            sb.append(resource[0]);
        }
        for (int i = 1; i < len; i++) {
            sb.append(separater);
            sb.append(resource[i]);
        }
        return sb.toString();
    }
}
