package com.fan.collect.module.jsbridge.api;

import java.util.HashMap;
import java.util.Map;

public class WebManager {

    //内置JSAPI集合
    private Map<String, Class> mJsApis = new HashMap<>();
    private String mJsBridge = null;

    private static volatile WebManager sInstance;
    public static WebManager getInstance(){
        if(sInstance == null){
            synchronized (WebManager.class){
                if(sInstance == null){
                    sInstance = new WebManager();
                }
            }
        }
        return sInstance;
    }
}
