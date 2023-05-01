package com.fan.collect.module.jsbridge.api.param;

import android.text.TextUtils;
import com.fan.collect.base.utils.Logger;
import com.fan.collect.module.jsbridge.api.callback.CallbackH5;
import com.fan.collect.module.jsbridge.api.utils.EmptyUtils;

public class JsCallParam extends BaseParam{

    public int callbackId = -1;

    public String sessionId;

    public String funcName;

    public String paramStr;

    public static JsCallParam fromJson(String json){
        JsCallParam result = null;

        try {
          result = fromJson(json,JsCallParam.class);

          if(!EmptyUtils.isEmpty(result)){
              result.mCallBack = new CallbackH5();
              result.mCallBack.funcName = result.funcName;
              result.mCallBack.sessionId = result.sessionId;
              result.mCallBack.callBackId = result.callbackId;
          }

        } catch (Exception e){
            e.printStackTrace();
            Logger.e("【JsCallParam】Error Parse Json：" + e.getMessage());
        }
        return result;
    }

    public boolean isValid(){
        return callbackId != -1 && !TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(funcName);
    }

}
