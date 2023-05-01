package com.fan.collect.module.jsbridge.api.param;

import android.text.TextUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.fan.collect.base.utils.Logger;
import com.fan.collect.module.jsbridge.api.callback.CallbackH5;
import com.fan.collect.module.jsbridge.api.utils.EmptyUtils;

public class BaseParam {

    public CallbackH5 mCallBack;

    public static <T>T fromJson(String json,Class<T> clazz){

        T result = null;

        if(EmptyUtils.isEmpty(clazz) || TextUtils.isEmpty(json)){
            Logger.w("【JsCallNative】Can not Parse Json：" + json);
            return null;
        }
        try {
            result = GsonUtils.fromJson(json,clazz);
        }catch (Exception e){
            Logger.e("【JsCallNative】Error Parse Json：" + e.getMessage());
        }
        return result;

    }


}
