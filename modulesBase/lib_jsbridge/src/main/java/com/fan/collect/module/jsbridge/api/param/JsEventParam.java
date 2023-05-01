package com.fan.collect.module.jsbridge.api.param;

import com.fan.collect.module.jsbridge.api.Constants;
import com.fan.collect.module.jsbridge.api.base.BaseWeb;
import org.json.JSONObject;

public class JsEventParam extends BaseParam{

    public String sessionId;

    public String eventName;

    public void broadcastEvent(BaseWeb baseWeb){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Constants.CALLBACK_ARG_NAME_EVENTNAME,eventName);
            baseWeb.getWebViewHolder().loadUrl(String.format(Constants.JS_MSG_BROADCAST, jsonObject.toString()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
