package com.ybg.oss.validator;


import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class AbstractAssert {

    public static void isBlank(String str, String message) throws Exception {
        if (StringUtils.isBlank(str)) {
            throw new Exception(message);
        }
    }

    public static void isNull(Object object, String message) throws Exception {
        if (object == null) {
            throw new Exception(message);
        }
    }
}
