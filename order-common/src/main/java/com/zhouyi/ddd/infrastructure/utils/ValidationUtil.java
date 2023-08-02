package com.zhouyi.ddd.infrastructure.utils;//package com.zhouyi.mall.infrastructure.utils;
//
//import com.zhouyi.mall.base.exception.ServiceException;
//import com.zhouyi.mall.base.model.result.ResultCodeEnum;
//
///**
// * @author jinyueWang
// * @date 2023/3/29
// * @Desc
// */
//public class ValidationUtil {
//
//    public static void isTrue(boolean expect, ResultCodeEnum resultCodeEnum, Object... params) {
//        if (!expect) {
//            throw new ServiceException(resultCodeEnum.getCode(),resultCodeEnum.getMessage());
//        }
//    }
//
//    public static void isFalse(boolean expect, ResultCodeEnum resultCodeEnum, Object... params) {
//        isTrue(!expect, resultCodeEnum, params);
//    }
//}
