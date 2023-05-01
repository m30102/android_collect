package com.fan.collect.module.jsbridge.api;

import android.text.TextUtils;
import com.fan.collect.base.utils.Logger;
import com.fan.collect.module.jsbridge.api.base.BaseWeb;
import com.fan.collect.module.jsbridge.api.param.JsCallParam;
import com.fan.collect.module.jsbridge.api.utils.EmptyUtils;

public abstract class JsApi {

    private static final String TAG = "JsApi";

    public abstract String method();

    public boolean accept(BaseWeb baseWeb, JsCallParam  jsCallParam){

        if(EmptyUtils.isEmpty(jsCallParam) || !jsCallParam.isValid()){

            Logger.e("【JsCallNative】js call native param is not valid ");
            return true;
        }

        if(!TextUtils.equals(method(),jsCallParam.funcName)){
            return false;
        }
        handle(baseWeb,jsCallParam);
        return true;

    }

    public abstract void handle(BaseWeb web,JsCallParam jsCallParam);
}
