package com.fan.collect.module.jsbridge.api.callback;

import android.os.Build;
import android.text.TextUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.fan.collect.base.BuildConfig;
import com.fan.collect.base.utils.Logger;
import com.fan.collect.module.jsbridge.api.Constants;
import com.fan.collect.module.jsbridge.api.base.BaseWeb;
import com.fan.collect.module.jsbridge.api.utils.EmptyUtils;
import com.fan.collect.module.jsbridge.api.utils.GsonUtil;
import java.util.Iterator;
import org.json.JSONObject;

public class CallbackH5 {


    public int callBackId = -1;

    public String sessionId;

    public String funcName;

    public int ret = ErrorCode.ERROR_NONE;

    public String errMsg = "ok";

    public void callBack(BaseWeb baseWeb, String resultJsonStr) {
        try {
            if (EmptyUtils.isEmpty(baseWeb)) {
                Logger.e("【CallbackH5】callback failed cause webview is null");
                return;
            }
            String commonJson = GsonUtils.toJson(this);
            final JSONObject jsonObject = new JSONObject(commonJson);

            if (!TextUtils.isEmpty(resultJsonStr)) {

                JSONObject resultJsonObj = new JSONObject(resultJsonStr);

                if (!EmptyUtils.isEmpty(resultJsonObj)) {
                    Iterator<String> keys = resultJsonObj.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (TextUtils.isEmpty(key)) {
                            continue;
                        }
                        jsonObject.put(key, resultJsonObj.get(key));
                    }
                }

            }

            Runnable runnable = () -> {

                String resultMsg = jsonObject.toString();

                if (BuildConfig.DEBUG) {
                    Logger.d("【CallbackH5】: " + funcName + " callback result: " + resultMsg);
                }

                if (baseWeb != null && baseWeb.mWebView != null) {
                    baseWeb.mWebView.evaluateJavascript(String.format(Constants.JS_MSG_CALLBACK, resultMsg));
                }

            };

            if (baseWeb != null && baseWeb.getWebViewHolder() != null
                    && baseWeb.getWebViewHolder().getHandler() != null) {
                baseWeb.getWebViewHolder().getHandler().post(runnable);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("【CallbackH5】" + funcName + " callback exception: " + e.getMessage());
        }
    }


}
