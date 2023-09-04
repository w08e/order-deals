package com.zhouyi.order.common.utils;


import com.zhouyi.order.common.exception.ValidationException;

/**
 * 校验工具类
 *
 * @author baiyan
 */
public class ValidationUtil {

    public static void isTrue(boolean expect, int code, Object... params) {
        if (!expect) {
            throw ValidationException.of(code, params);
        }
    }

    public static void isFalse(boolean expect, int code, Object... params) {
        isTrue(!expect, code, params);
    }

}
