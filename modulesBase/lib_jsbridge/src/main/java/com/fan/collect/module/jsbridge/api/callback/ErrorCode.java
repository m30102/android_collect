package com.fan.collect.module.jsbridge.api.callback;

import java.security.PublicKey;

public class ErrorCode {

    // 接口调用成功
    public static final int ERROR_NONE = 0;
    // 接口调用失败
    public static final int ERROR_FAIL = 1;
    // 业务执行失败
    public static final int ERROR_BUSINESS = 2;
    // 取消
    public static final int ERROR_CANCEL = -1;

}
